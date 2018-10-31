package biz.uniwear.batch.mappingsfunc;

import biz.uniwear.batch.apiclientudcart.ApiClient;
import biz.uniwear.batch.apiclientudcart.ApiException;
import biz.uniwear.batch.apiclientudcart.Configuration;

import biz.uniwear.batch.apiclientudcart.api.CatalogProductRepositoryV1Api;

import biz.uniwear.batch.apiclientudcart.model.CatalogDataProductInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class UDCart {

    private static final Logger logger = LogManager.getLogger();

    static String apiKey = "rm0bolwuc25y24g8jza2vc2x15g3zf8u";

    public static void main(String[] args) {
        logger.traceEntry();

//        CatalogCategoryLinkManagementV1Api.
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.addDefaultHeader("Authorization", "Bearer " + apiKey);
        defaultClient.setDebugging(true);

        //

//
        CatalogProductRepositoryV1Api cProductRepository = new CatalogProductRepositoryV1Api();

        String test = null;

        try {
            CatalogDataProductInterface result = cProductRepository.catalogProductRepositoryV1GetGet("TEST-01", null, null, null);

            logger.debug(result.getSku());

        } catch (ApiException e) {
            logger.catching(e);
        }



    logger.traceExit();
    }
}
