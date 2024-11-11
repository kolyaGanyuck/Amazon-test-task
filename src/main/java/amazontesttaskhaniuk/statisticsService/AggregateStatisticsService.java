package amazontesttaskhaniuk.statisticsService;

import amazontesttaskhaniuk.model.*;
import amazontesttaskhaniuk.model.aggregatedModel.TotalReportContainerByAsin;
import amazontesttaskhaniuk.model.aggregatedModel.TotalReportContainerByDate;
import amazontesttaskhaniuk.repository.ReportContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AggregateStatisticsService {
    private final ReportContainerRepository repository;

    @Cacheable(value = "totalSalesByDate", key = "'aggregatedSalesByDate'")
    public TotalReportContainerByDate getAggregatedSalesByDate() {
        List<ReportContainer> reportContainers = repository.findAll();
        TotalReportContainerByDate totalReportContainer = new TotalReportContainerByDate();

        List<SalesByDate> salesByDates = new ArrayList<>();
        List<TrafficByDate> trafficByDates = new ArrayList<>();


        if (reportContainers.size() > 0) {
            for (int i = 0; i < reportContainers.size(); i++) {

                if (reportContainers.get(i) != null) {
                    ReportContainer reportContainer = reportContainers.get(i);

                    SalesByDate firstSalesByDate = reportContainer.getSalesAndTrafficByDate().get(0).getSalesByDate();
                    TrafficByDate firstTrafficByDate = reportContainer.getSalesAndTrafficByDate().get(0).getTrafficByDate(); // Get the corresponding TrafficByDate for the first SalesByDate

                    for (int j = 1; j < reportContainer.getSalesAndTrafficByDate().size(); j++) {
                        SalesByDate salesByDate = reportContainer.getSalesAndTrafficByDate().get(j).getSalesByDate();
                        TrafficByDate trafficByDate = reportContainer.getSalesAndTrafficByDate().get(j).getTrafficByDate();

                        Amount amount = new Amount(
                                firstSalesByDate.getOrderedProductSales().getAmount() + salesByDate.getOrderedProductSales().getAmount(),
                                salesByDate.getOrderedProductSales().getCurrencyCode()
                        );
                        Amount amountB2B = new Amount(
                                firstSalesByDate.getOrderedProductSalesB2B().getAmount() + salesByDate.getOrderedProductSalesB2B().getAmount(),
                                salesByDate.getOrderedProductSalesB2B().getCurrencyCode()
                        );
                        salesByDate.setOrderedProductSales(new OrderedProductSales(amount));
                        salesByDate.setOrderedProductSalesB2B(new OrderedProductSalesB2B(amountB2B));

                        salesByDate.setUnitsOrdered(firstSalesByDate.getUnitsOrdered() + salesByDate.getUnitsOrdered());
                        salesByDate.setUnitsOrderedB2B(firstSalesByDate.getUnitsOrderedB2B() + salesByDate.getUnitsOrderedB2B());

                        salesByDate.setTotalOrderItems(firstSalesByDate.getTotalOrderItems() + salesByDate.getTotalOrderItems());
                        salesByDate.setTotalOrderItemsB2B(firstSalesByDate.getTotalOrderItemsB2B() + salesByDate.getTotalOrderItemsB2B());

                        Amount avgSalesPerOrderItem = new Amount(
                                (firstSalesByDate.getAverageSalesPerOrderItem().getAmount() + salesByDate.getAverageSalesPerOrderItem().getAmount()) / 2,
                                salesByDate.getAverageSalesPerOrderItem().getCurrencyCode()
                        );
                        salesByDate.setAverageSalesPerOrderItem(avgSalesPerOrderItem);

                        Amount avgSalesPerOrderItemB2B = new Amount(
                                (firstSalesByDate.getAverageSalesPerOrderItemB2B().getAmount() + salesByDate.getAverageSalesPerOrderItemB2B().getAmount()) / 2,
                                salesByDate.getAverageSalesPerOrderItemB2B().getCurrencyCode()
                        );
                        salesByDate.setAverageSalesPerOrderItemB2B(avgSalesPerOrderItemB2B);

                        salesByDate.setAverageUnitsPerOrderItem(
                                (firstSalesByDate.getAverageUnitsPerOrderItem() + salesByDate.getAverageUnitsPerOrderItem()) / 2
                        );
                        salesByDate.setAverageUnitsPerOrderItemB2B(
                                (firstSalesByDate.getAverageUnitsPerOrderItemB2B() + salesByDate.getAverageUnitsPerOrderItemB2B()) / 2
                        );

                        Amount avgSellingPrice = new Amount(
                                (firstSalesByDate.getAverageSellingPrice().getAmount() + salesByDate.getAverageSellingPrice().getAmount()) / 2,
                                salesByDate.getAverageSellingPrice().getCurrencyCode()
                        );
                        salesByDate.setAverageSellingPrice(avgSellingPrice);

                        Amount avgSellingPriceB2B = new Amount(
                                (firstSalesByDate.getAverageSellingPriceB2B().getAmount() + salesByDate.getAverageSellingPriceB2B().getAmount()) / 2,
                                salesByDate.getAverageSellingPriceB2B().getCurrencyCode()
                        );
                        salesByDate.setAverageSellingPriceB2B(avgSellingPriceB2B);

                        salesByDate.setUnitsRefunded(firstSalesByDate.getUnitsRefunded() + salesByDate.getUnitsRefunded());
                        salesByDate.setClaimsGranted(firstSalesByDate.getClaimsGranted() + salesByDate.getClaimsGranted());

                        Amount claimsAmt = new Amount(
                                firstSalesByDate.getClaimsAmount().getAmount() + salesByDate.getClaimsAmount().getAmount(),
                                salesByDate.getClaimsAmount().getCurrencyCode()
                        );
                        salesByDate.setClaimsAmount(claimsAmt);

                        Amount shippedProductSales = new Amount(
                                firstSalesByDate.getShippedProductSales().getAmount() + salesByDate.getShippedProductSales().getAmount(),
                                salesByDate.getShippedProductSales().getCurrencyCode()
                        );
                        salesByDate.setShippedProductSales(shippedProductSales);

                        salesByDate.setUnitsShipped(firstSalesByDate.getUnitsShipped() + salesByDate.getUnitsShipped());
                        salesByDate.setOrdersShipped(firstSalesByDate.getOrdersShipped() + salesByDate.getOrdersShipped());


                        trafficByDate.setBrowserPageViews(firstTrafficByDate.getBrowserPageViews() + trafficByDate.getBrowserPageViews());
                        trafficByDate.setBrowserPageViewsB2B(firstTrafficByDate.getBrowserPageViewsB2B() + trafficByDate.getBrowserPageViewsB2B());
                        trafficByDate.setMobileAppPageViews(firstTrafficByDate.getMobileAppPageViews() + trafficByDate.getMobileAppPageViews());
                        trafficByDate.setMobileAppPageViewsB2B(firstTrafficByDate.getMobileAppPageViewsB2B() + trafficByDate.getMobileAppPageViewsB2B());
                        trafficByDate.setPageViews(firstTrafficByDate.getPageViews() + trafficByDate.getPageViews());
                        trafficByDate.setPageViewsB2B(firstTrafficByDate.getPageViewsB2B() + trafficByDate.getPageViewsB2B());
                        trafficByDate.setBrowserSessions(firstTrafficByDate.getBrowserSessions() + trafficByDate.getBrowserSessions());
                        trafficByDate.setBrowserSessionsB2B(firstTrafficByDate.getBrowserSessionsB2B() + trafficByDate.getBrowserSessionsB2B());
                        trafficByDate.setMobileAppSessions(firstTrafficByDate.getMobileAppSessions() + trafficByDate.getMobileAppSessions());
                        trafficByDate.setMobileAppSessionsB2B(firstTrafficByDate.getMobileAppSessionsB2B() + trafficByDate.getMobileAppSessionsB2B());
                        trafficByDate.setSessions(firstTrafficByDate.getSessions() + trafficByDate.getSessions());
                        trafficByDate.setSessionsB2B(firstTrafficByDate.getSessionsB2B() + trafficByDate.getSessionsB2B());
                        trafficByDate.setBuyBoxPercentage((firstTrafficByDate.getBuyBoxPercentage() + trafficByDate.getBuyBoxPercentage()) / 2);
                        trafficByDate.setBuyBoxPercentageB2B((firstTrafficByDate.getBuyBoxPercentageB2B() + trafficByDate.getBuyBoxPercentageB2B()) / 2);
                        trafficByDate.setOrderItemSessionPercentage((firstTrafficByDate.getOrderItemSessionPercentage() + trafficByDate.getOrderItemSessionPercentage()) / 2);
                        trafficByDate.setOrderItemSessionPercentageB2B((firstTrafficByDate.getOrderItemSessionPercentageB2B() + trafficByDate.getOrderItemSessionPercentageB2B()) / 2);
                        trafficByDate.setUnitSessionPercentage((firstTrafficByDate.getUnitSessionPercentage() + trafficByDate.getUnitSessionPercentage()) / 2);
                        trafficByDate.setUnitSessionPercentageB2B((firstTrafficByDate.getUnitSessionPercentageB2B() + trafficByDate.getUnitSessionPercentageB2B()) / 2);
                        trafficByDate.setAverageOfferCount(firstTrafficByDate.getAverageOfferCount() + trafficByDate.getAverageOfferCount());
                        trafficByDate.setAverageParentItems(firstTrafficByDate.getAverageParentItems() + trafficByDate.getAverageParentItems());
                        trafficByDate.setFeedbackReceived(firstTrafficByDate.getFeedbackReceived() + trafficByDate.getFeedbackReceived());
                        trafficByDate.setNegativeFeedbackReceived(firstTrafficByDate.getNegativeFeedbackReceived() + trafficByDate.getNegativeFeedbackReceived());
                        trafficByDate.setReceivedNegativeFeedbackRate((firstTrafficByDate.getReceivedNegativeFeedbackRate() + trafficByDate.getReceivedNegativeFeedbackRate()) / 2);

                        firstSalesByDate = salesByDate;
                        firstTrafficByDate = trafficByDate;
                    }

                    salesByDates.add(firstSalesByDate);
                    trafficByDates.add(firstTrafficByDate);

                    totalReportContainer.setTotalSalesByDate(salesByDates);
                    totalReportContainer.setTotalTrafficByDate(trafficByDates);
                }
            }
        }
        return totalReportContainer;
    }

    @Cacheable(value = "totalSalesByAsin", key = "'aggregatedSalesByAsin'")

    public TotalReportContainerByAsin getAggregatedSalesByAsin() {
        List<ReportContainer> reportContainers = repository.findAll();

        TotalReportContainerByAsin totalReportContainer = new TotalReportContainerByAsin();

        List<SalesByAsin> salesByAsins = new ArrayList<>();
        List<TrafficByAsin> trafficByAsins = new ArrayList<>();

        if (reportContainers.size() > 0) {
            for (int i = 0; i < reportContainers.size(); i++) {
                if (reportContainers.get(i) != null) {
                    ReportContainer reportContainer = reportContainers.get(i);

                    SalesByAsin firstSalesByAsin = reportContainer.getSalesAndTrafficByAsin().get(0).getSalesByAsin();
                    TrafficByAsin firstTrafficByAsin = reportContainer.getSalesAndTrafficByAsin().get(0).getTrafficByAsin();

                    for (int j = 1; j < reportContainer.getSalesAndTrafficByAsin().size(); j++) {
                        SalesByAsin salesByAsin = reportContainer.getSalesAndTrafficByAsin().get(j).getSalesByAsin();
                        TrafficByAsin trafficByAsin = reportContainer.getSalesAndTrafficByAsin().get(j).getTrafficByAsin();

                        firstSalesByAsin.setUnitsOrdered(firstSalesByAsin.getUnitsOrdered() + salesByAsin.getUnitsOrdered());
                        firstSalesByAsin.setUnitsOrderedB2B(firstSalesByAsin.getUnitsOrderedB2B() + salesByAsin.getUnitsOrderedB2B());
                        firstSalesByAsin.setTotalOrderItems(firstSalesByAsin.getTotalOrderItems() + salesByAsin.getTotalOrderItems());

                        firstSalesByAsin.setTotalOrderItemsB2B(firstSalesByAsin.getTotalOrderItemsB2B() + salesByAsin.getTotalOrderItemsB2B());
                        Amount amountOrderedProductSales = new Amount(firstSalesByAsin.getOrderedProductSales().getAmount() + salesByAsin.getOrderedProductSales().getAmount(), firstSalesByAsin.getOrderedProductSales().getCurrencyCode());
                        Amount amountOrderedProductSalesB2B = new Amount(firstSalesByAsin.getOrderedProductSalesB2B().getAmount() + salesByAsin.getOrderedProductSalesB2B().getAmount(), firstSalesByAsin.getOrderedProductSalesB2B().getCurrencyCode());

                        firstSalesByAsin.setOrderedProductSales(new OrderedProductSales(amountOrderedProductSales));
                        firstSalesByAsin.setOrderedProductSalesB2B(new OrderedProductSales(amountOrderedProductSalesB2B));


                        firstTrafficByAsin.setBrowserSessions(firstTrafficByAsin.getBrowserSessions() + trafficByAsin.getBrowserSessions());
                        firstTrafficByAsin.setBrowserSessionsB2B(firstTrafficByAsin.getBrowserSessionsB2B() + trafficByAsin.getBrowserSessionsB2B());
                        firstTrafficByAsin.setMobileAppSessions(firstTrafficByAsin.getMobileAppSessions() + trafficByAsin.getMobileAppSessions());
                        firstTrafficByAsin.setMobileAppSessionsB2B(firstTrafficByAsin.getMobileAppSessionsB2B() + trafficByAsin.getMobileAppSessionsB2B());
                        firstTrafficByAsin.setSessions(firstTrafficByAsin.getSessions() + trafficByAsin.getSessions());
                        firstTrafficByAsin.setSessionsB2B(firstTrafficByAsin.getSessionsB2B() + trafficByAsin.getSessionsB2B());
                        firstTrafficByAsin.setBrowserPageViews(firstTrafficByAsin.getBrowserPageViews() + trafficByAsin.getBrowserPageViews());
                        firstTrafficByAsin.setBrowserPageViewsB2B(firstTrafficByAsin.getBrowserPageViewsB2B() + trafficByAsin.getBrowserPageViewsB2B());
                        firstTrafficByAsin.setMobileAppPageViews(firstTrafficByAsin.getMobileAppPageViews() + trafficByAsin.getMobileAppPageViews());
                        firstTrafficByAsin.setMobileAppPageViewsB2B(firstTrafficByAsin.getMobileAppPageViewsB2B() + trafficByAsin.getMobileAppPageViewsB2B());
                        firstTrafficByAsin.setPageViews(firstTrafficByAsin.getPageViews() + trafficByAsin.getPageViews());
                        firstTrafficByAsin.setPageViewsB2B(firstTrafficByAsin.getPageViewsB2B() + trafficByAsin.getPageViewsB2B());

                        // Агрегація відсоткових значень
                        firstTrafficByAsin.setBrowserSessionPercentage(
                                firstTrafficByAsin.getBrowserSessionPercentage() + trafficByAsin.getBrowserSessionPercentage()
                        );
                        firstTrafficByAsin.setBrowserSessionPercentageB2B(
                                firstTrafficByAsin.getBrowserSessionPercentageB2B() + trafficByAsin.getBrowserSessionPercentageB2B()
                        );
                        firstTrafficByAsin.setMobileAppSessionPercentage(
                                firstTrafficByAsin.getMobileAppSessionPercentage() + trafficByAsin.getMobileAppSessionPercentage()
                        );
                        firstTrafficByAsin.setMobileAppSessionPercentageB2B(
                                firstTrafficByAsin.getMobileAppSessionPercentageB2B() + trafficByAsin.getMobileAppSessionPercentageB2B()
                        );
                        firstTrafficByAsin.setSessionPercentage(
                                firstTrafficByAsin.getSessionPercentage() + trafficByAsin.getSessionPercentage()
                        );
                        firstTrafficByAsin.setSessionPercentageB2B(
                                firstTrafficByAsin.getSessionPercentageB2B() + trafficByAsin.getSessionPercentageB2B()
                        );
                        firstTrafficByAsin.setBrowserPageViewsPercentage(
                                firstTrafficByAsin.getBrowserPageViewsPercentage() + trafficByAsin.getBrowserPageViewsPercentage()
                        );
                        firstTrafficByAsin.setBrowserPageViewsPercentageB2B(
                                firstTrafficByAsin.getBrowserPageViewsPercentageB2B() + trafficByAsin.getBrowserPageViewsPercentageB2B()
                        );
                        firstTrafficByAsin.setMobileAppPageViewsPercentage(
                                firstTrafficByAsin.getMobileAppPageViewsPercentage() + trafficByAsin.getMobileAppPageViewsPercentage()
                        );
                        firstTrafficByAsin.setMobileAppPageViewsPercentageB2B(
                                firstTrafficByAsin.getMobileAppPageViewsPercentageB2B() + trafficByAsin.getMobileAppPageViewsPercentageB2B()
                        );
                        firstTrafficByAsin.setPageViewsPercentage(
                                firstTrafficByAsin.getPageViewsPercentage() + trafficByAsin.getPageViewsPercentage()
                        );
                        firstTrafficByAsin.setPageViewsPercentageB2B(
                                firstTrafficByAsin.getPageViewsPercentageB2B() + trafficByAsin.getPageViewsPercentageB2B()
                        );
                        firstTrafficByAsin.setBuyBoxPercentage(
                                firstTrafficByAsin.getBuyBoxPercentage() + trafficByAsin.getBuyBoxPercentage()
                        );
                        firstTrafficByAsin.setBuyBoxPercentageB2B(
                                firstTrafficByAsin.getBuyBoxPercentageB2B() + trafficByAsin.getBuyBoxPercentageB2B()
                        );
                        firstTrafficByAsin.setUnitSessionPercentage(
                                firstTrafficByAsin.getUnitSessionPercentage() + trafficByAsin.getUnitSessionPercentage()
                        );
                        firstTrafficByAsin.setUnitSessionPercentageB2B(
                                firstTrafficByAsin.getUnitSessionPercentageB2B() + trafficByAsin.getUnitSessionPercentageB2B()
                        );
                    }
                    salesByAsins.add(firstSalesByAsin);
                    trafficByAsins.add(firstTrafficByAsin);
                }
                totalReportContainer.setTotalSalesByAsin(salesByAsins);
                totalReportContainer.setTotalTrafficByAsin(trafficByAsins);
            }
        }
        return totalReportContainer;
    }
}
