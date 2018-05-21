package ru.job4j.exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Modellig Exchange Market.
 */
public class ExchangeMarket {

    private final List<MarketDepth> marketDepths = new ArrayList<>();

    /**
     * Add request for cell or buy stocks.
     * @param book name of a company
     * @param actionOfRequest bid or ask
     * @param price for deal
     * @param volume for deal
     * @return int id
     */
    public int addRequest(final String book,
                           final Request.Action actionOfRequest,
                           final int price,
                           final int volume) {
        Request buffer = new Request(book, Request.Type.ADD, actionOfRequest, price, volume);
        Optional<MarketDepth> targetMarketDepth = findMarketDepthByBook(book);
        if (targetMarketDepth.isPresent()) {
            targetMarketDepth.get().addRequest(buffer);
        } else {
            createNewMarketDepth(book).addRequest(buffer);
        }
        return buffer.getId();

    }

    /**
     * Delete request for cell or buy stocks.
     * @param id of request for deleting
     * @param book name of a company
     * @param actionOfRequest bid or ask
     * @return boolean of result (true if find and delete)
     */
    public boolean deleteRequest(final int id,
                                 final String book,
                                 final Request.Action actionOfRequest) {
        boolean result = false;
        Optional<MarketDepth> targetMarketDepth = findMarketDepthByBook(book);
        if (targetMarketDepth.isPresent()) {
            Request buffer = new Request(id, book, Request.Type.DELETE, actionOfRequest);
            result = targetMarketDepth.get().removeRequest(buffer);
        }
        return result;


    }

    /**
     * Number of companies in the market.
     * @return int number.
     */
    public int getNumberMarketDepths() {
        return this.marketDepths.size();
    }

    /**
     * Get list of companies.
     * @return list of companies.
     */
    public List<MarketDepth> getMarketDepths() {
        return this.marketDepths;
    }

    private Optional<MarketDepth> findMarketDepthByBook(final String book) {
        Optional<MarketDepth> result = Optional.empty();
        for (MarketDepth market : marketDepths) {
            if (market.getName().equals(book)) {
                result = Optional.of(market);
                break;
            }
        }
        return result;
    }

    private MarketDepth createNewMarketDepth(final String book) {
        MarketDepth newMarketDepthByBook = new MarketDepth(book);
        marketDepths.add(newMarketDepthByBook);
        return newMarketDepthByBook;
    }


}