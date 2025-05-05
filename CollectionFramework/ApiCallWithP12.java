package CollectionFramework;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;

public class ApiCallWithP12 {
    public static void main(String[] args) {
        try{
            String p12FilePath = "/path/to/your/certificate.p12";
            String p12Password = "your_password";

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(p12FilePath);
            keyStore.load(fis, p12Password.toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, p12Password.toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            URL url = new URL("");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


// epfoelasticquery

//package com.finnable.inhouseEpfo.fetchEpfo;
//
//import com.amazonaws.auth.AWS4Signer;
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.regions.Regions;
//import com.finnable.util.AWSRequestSigningApacheInterceptor;
//import com.finnable.util.CommonUtil;
//import com.google.gson.Gson;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpRequestInterceptor;
//import org.opensearch.action.search.SearchRequest;
//import org.opensearch.action.search.SearchResponse;
//import org.opensearch.client.RequestOptions;
//import org.opensearch.client.RestClient;
//import org.opensearch.client.RestHighLevelClient;
//import org.opensearch.index.query.BoolQueryBuilder;
//import org.opensearch.index.query.QueryBuilders;
//import org.opensearch.search.SearchHit;
//import org.opensearch.search.builder.SearchSourceBuilder;
//import java.io.IOException;
//import java.util.Map;
//
//public class EPFOElasticQuery {
//    static final String elasticHost = "search-finnable-dev-index-pxy7ijufntpdw3xkhonizx3uq4.us-east-1.es.amazonaws.com";
//    static final String elasticIndexName = "companies-epfo_new_mapping";
//
//    public static void main(String[] args) throws IOException {
//        System.out.println(new Gson().toJson(elasticQuery("MS M F JAIN", "Nagpur")));
//    }
//
//    public static EPFOElasticQueryResponse onlyCompanyQuery(String companyName) {
//        try{
//            RestHighLevelClient client = elasticClient();
//            SearchRequest searchRequest = new SearchRequest(elasticIndexName);
//            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            sourceBuilder.query(QueryBuilders.matchQuery("establishmentName", companyName));
//            searchRequest.source(sourceBuilder);
//            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            if (hits.length > 0) {
//                Map<String, Object> sourceAsMap = hits[0].getSourceAsMap();
//                String companyId = hits[0].getId().substring(5, 12);
//                String companyNameFromElastic = (String) sourceAsMap.get("establishmentName");
//                EPFOElasticQueryResponse response = new EPFOElasticQueryResponse();
//                response.setCompanyId(companyId);
//                response.setCompanyName(companyNameFromElastic);
//                System.out.println("onlyCompanyQuery => " + new Gson().toJson(response));
//                return response;
//            } else {
//                System.out.println("No matching documents found in elastic.");
//                return null;
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static EPFOElasticQueryResponse companyAndAddressQuery(String companyName, String officeAddress) {
//        try {
//            RestHighLevelClient client = elasticClient();
//            SearchRequest searchRequest = new SearchRequest(elasticIndexName);
//            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
//                    .must(QueryBuilders.matchQuery("establishmentName", companyName))
//                    .must(QueryBuilders.matchQuery("OfficeName", officeAddress));
//            sourceBuilder.query(boolQueryBuilder);
//            searchRequest.source(sourceBuilder);
//            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            if (hits.length > 0) {
//                Map<String, Object> sourceAsMap = hits[0].getSourceAsMap();
//                String companyId = hits[0].getId().substring(5, 12);
//                String companyNameFromElastic = (String) sourceAsMap.get("establishmentName");
//                EPFOElasticQueryResponse response = new EPFOElasticQueryResponse();
//                response.setCompanyId(companyId);
//                response.setCompanyName(companyNameFromElastic);
//                System.out.println("companyAndAddressQuery => " + new Gson().toJson(response));
//                return response;
//            } else {
//                System.out.println("No matching documents found in elastic.");
//                return null;
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static EPFOElasticQueryResponse elasticQuery(String companyName, String officeAddress) {
//        try {
//            EPFOElasticQueryResponse onlyCompanyQueryResponse = onlyCompanyQuery(companyName);
//            EPFOElasticQueryResponse companyAndAddressQueryResponse = null;
//            if (officeAddress!=null && (!officeAddress.isEmpty())) {
//                companyAndAddressQueryResponse = companyAndAddressQuery(companyName, officeAddress);
//            }
//            if (onlyCompanyQueryResponse==null && companyAndAddressQueryResponse==null)
//                return null;
//            if (onlyCompanyQueryResponse==null)
//                return companyAndAddressQueryResponse;
//            if (companyAndAddressQueryResponse == null)
//                return onlyCompanyQueryResponse;
//            if (onlyCompanyQueryResponse.getCompanyName().equalsIgnoreCase(companyAndAddressQueryResponse.getCompanyName()))
//                return companyAndAddressQueryResponse;
//            return onlyCompanyQueryResponse;
////            double companyNameCutoff = 0.988;
////            boolean nameMatchForOnlyCompany = CommonUtil.companyNameMatchForEPFO(companyName, onlyCompanyQueryResponse.getCompanyName(), companyNameCutoff);
////            boolean nameMatchForCompanyAndAddress = CommonUtil.companyNameMatchForEPFO(companyName, companyAndAddressQueryResponse.getCompanyName(), companyNameCutoff);
////            if (nameMatchForOnlyCompany && nameMatchForCompanyAndAddress) {
////                return companyAndAddressQueryResponse;
////            }
////            else if (nameMatchForCompanyAndAddress) {
////                return companyAndAddressQueryResponse;
////            }
////            else {
////                return onlyCompanyQueryResponse;
////            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//    private static RestHighLevelClient elasticClient() {
//        return esClient(EPFOElasticQuery.elasticHost);
//    }
//
//    private static RestHighLevelClient esClient(String elasticHost) {
//        String serviceName = "es";
//        AWS4Signer signer = new AWS4Signer();
//        signer.setServiceName(serviceName);
//        signer.setRegionName(Regions.US_EAST_1.getName());
//        AWSCredentialsProvider credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
//        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(
//                serviceName, signer, credentialsProvider);
//        return new RestHighLevelClient(RestClient.builder(new HttpHost(elasticHost, 443, "https"))
//                .setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor)));
//    }
//
//    @AllArgsConstructor @NoArgsConstructor @Setter @Getter
//    public static class EPFOElasticQueryResponse {
//        private String companyName;
//        private String companyId;
//    }
//}


// Generate preclosure letter

//package com.finnable.customer.PreClosure;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.GetObjectRequest;
//import com.amazonaws.services.s3.model.S3Object;
//import com.finnable.dbmappers.*;
//        import com.finnable.opsDashboardAPI.LoanTabAPI;
//import com.finnable.util.CommonUtil;
//import com.finnable.util.Constants;
//import com.finnable.util.HWAWSCredentials;
//import com.itextpdf.io.font.FontConstants;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
//import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
//import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.UUID;
//
//public class GenerateForeclosureLetter {
//
//    /*
//    statementGenerationDate
//    validity
//    interestPerDay
//    loanAccountNo
//    pos
//    interest
//    prePaymentCharges
//    gst
//    totalAmount
//    interestPerDayAfterValidity
//    payeeName
//    bankName
//    accountNo
//    type
//    ifsc
//    branchAddress
//    foreclosureAmount
//    amountValidity
//    additionalPerDayInterest
//     */
//
//    PdfFont font;
//    DB_User dbUser;
//    DB_Loan dbLoan;
//    String paymentMethod, paymentDate;
//    String acceptedPaymentOptions, wrongPaymentOptions, demandDraftPayments, branchAddress;
//    String statementGenerationDate, validity, interestPerDay, totalAmount;
//    LoanTabAPI.PreClosureDetailModal preClosureDetailModal;
//    NBFCAccountConfig.NBFCAccountDetail nbfcAccountDetail;
//    String template;
//    PDDocument pdDocument;
//
//
//    public GenerateForeclosureLetter(DB_User dbUser, DB_Loan dbLoan, String paymentMethod, String paymentDate) {
//        this.dbUser = dbUser;
//        this.dbLoan = dbLoan;
//        this.paymentMethod = StringUtils.isNotBlank(paymentMethod) ? paymentMethod : DB_LoanUpdateRequest.PaymentType.OnlinePayment.getName();
//        this.paymentDate = StringUtils.isNotBlank(paymentDate) ? paymentDate : LocalDate.now().toString();
//    }
//
//    public static void main(String[] args) {
//        DB_User dbUser = DB_User.fetchUserByMobileNo("9643120131");
//        DB_Loan dbLoan = DB_Loan.fetchLatestLoanByUserID(dbUser.getUserID());
//        new GenerateForeclosureLetter(dbUser, dbLoan, null, null).create();
//    }
//
//    public String create() {
//        String fileName = "/tmp/" + "ForeclosureLetter" + UUID.randomUUID() + "-" + dbLoan.getLoanID() + ".pdf";
//        File fileForeclosureLetter = new File(fileName);
//        getCustomFont();
//        initializeData();
//        loadPdfTemplate();
//        populateData();
//        String url = saveAndUploadToS3(fileName, fileForeclosureLetter).toString();
//        if (url != null)
//            return fileForeclosureLetter.getName();
//        return null;
//    }
//
//    private void getCustomFont() {
//        try {
//            font = PdfFontFactory.createFont(FontConstants.HELVETICA);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void initializeData() {
//
//        DB_CreditLine creditLine = DB_CreditLine.load(dbLoan.getCreditLineID(), dbLoan.getUserID());
//        List<DB_PaymentHistory> paidEMIList = DB_PaymentHistory.paymentHistoryByLoanID(dbLoan.getLoanID());
//        List<DB_PaymentDue> futureEMIList = DB_PaymentDue.fetchAllPaymentDuesByLoanID(dbLoan.getLoanID());
//        this.preClosureDetailModal = LoanTabAPI.fetchLoanPreClosureDetails(futureEMIList, paidEMIList, creditLine, dbLoan, LocalDate.now().toString(), dbUser,DB_LoanUpdateRequest.RequestType.LoanClose.getName(),paymentMethod);
//        this.nbfcAccountDetail = DB_Configuration.getNBFCAccountDetails(dbLoan.getNbfcInstitution());
//
//        this.statementGenerationDate = CommonUtil.formatDate(LocalDate.now().toString(), Constants.LOCAL_DATE_FORMAT_SERVER, Constants.FORMAT_AADHAAR_DATE);
//        this.validity = CommonUtil.formatDate(LocalDate.parse(paymentDate).plusDays(12).toString(), Constants.LOCAL_DATE_FORMAT_SERVER, Constants.FORMAT_AADHAAR_DATE);
//
//        this.interestPerDay = String.valueOf(preClosureDetailModal.getPerDayInterest());
//        this.totalAmount = String.valueOf((int)preClosureDetailModal.getTotalPreClosureAmount());
//        this.branchAddress = nbfcAccountDetail != null ? nbfcAccountDetail.getBranch_Address() : "";
//        this.demandDraftPayments = "Submit original DD in any " + (nbfcAccountDetail!=null? nbfcAccountDetail.Bank_Name:"branch") + " near you";
//
//        switch (DB_NBFCMapping.NBFCName.getNbfc(dbLoan.getNbfcInstitution()) ){
//            case DMI:
//                this.template = "DMI_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "";
//                this.acceptedPaymentOptions = "DD/IMPS/NEFT/RTGS/Cash/Cheque";
//                break;
//
//            case Finnable:
//            case VCPL:
//            case AXIS:
//                this.template = "Finnable_And_VCPL_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "";
//                this.acceptedPaymentOptions = "DD/IMPS/NEFT/RTGS/Cash/Cheque";
//                break;
//
//            case Northern_Arc:
//                this.template = "NorthernArc_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "";
//                this.acceptedPaymentOptions = "DD/IMPS/NEFT/RTGS/Cash/Cheque";
//                this.branchAddress  = "Any nearest branch";
//                break;
//
//            case TVS:
//            case TVS_Online:
//                this.template = "TVS_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "Demand Draft not allowed";
//                this.acceptedPaymentOptions = "IMPS/NEFT/RTGS/Cheque";
//                break;
//
//            case HDB:
//            case HDB_Direct:
//                this.template = "HDB_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "Demand Draft not allowed";
//                this.acceptedPaymentOptions = "IMPS/NEFT/RTGS";
//                break;
//
//            case Utkarsh:
//                this.template = "Utkarsh_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "Demand Draft, IMPS, UPI, Cheque, Cash not allowed";
//                this.acceptedPaymentOptions = "RTGS/NEFT";
//                break;
//
//            case Piramal:
//            case TataCapital:
//            default:
//                this.template = "Piramal_ForeClosure_Template.pdf";
//                this.wrongPaymentOptions = "";
//                this.acceptedPaymentOptions = "";
//                break;
//        }
//    }
//
//    private void loadPdfTemplate() {
//        try {
//            AmazonS3 s3Client = HWAWSCredentials.s3Client();
//            S3Object s3object = s3Client.getObject(new GetObjectRequest(DB_Configuration.fetchS3BackendDocumentBucket() , "foreclosureStatementTemplates/" + template));
//            File foreclosureStatement = CommonUtil.convertInputStreamToFile(s3object.getObjectContent(), "/tmp/ForeclosureLetterTemplate.pdf");
//            File foreclosureStatementTemplate = new File(foreclosureStatement.toURI());
//            pdDocument = PDDocument.load(foreclosureStatementTemplate);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void populateData() {
//
//        setField("statementGenerationDate", statementGenerationDate);
//        setField("validity", validity);
//        setField("payByDate", validity);
//        setField("interestPerDay", "Rs." +  interestPerDay);
//        setField("loanAccountNo", "FN" + dbLoan.getLoanAccountNo());
//        setField("pos", "Rs." + preClosureDetailModal.getPrincipalOutstanding());
//        setField("interest", "Rs." + preClosureDetailModal.getPostEMIInterest());
//        setField("prePaymentCharges", "Rs." + preClosureDetailModal.getPreClosureCharges());
//        setField("gst", "Rs." + preClosureDetailModal.getGstOnCharges());
//        setField("totalAmount", "Rs." + totalAmount);
//        setField("interestPerDayAfterValidity", "Rs." + interestPerDay);
//        setField("payeeName", nbfcAccountDetail != null && nbfcAccountDetail.getAccount_Name() != null ? nbfcAccountDetail.getAccount_Name() : "");
//        setField("payeeNameOnDD", nbfcAccountDetail != null && nbfcAccountDetail.getAccount_Name() != null ? nbfcAccountDetail.getAccount_Name() : "");
//        setField("bankName", nbfcAccountDetail != null && nbfcAccountDetail.getBank_Name() != null ? processBankName(nbfcAccountDetail.getBank_Name()) : "");
//        setField("bankBranch", nbfcAccountDetail != null && nbfcAccountDetail.getBank_Name() != null ? processBankName(nbfcAccountDetail.getBank_Name()) : "");
//        setField("accountNo", nbfcAccountDetail != null && nbfcAccountDetail.getAcc_Number() != null ? nbfcAccountDetail.getAcc_Number() : "");
//        setField("type", nbfcAccountDetail != null && nbfcAccountDetail.getType() != null ? nbfcAccountDetail.getType() : "");
//        setField("ifsc", nbfcAccountDetail != null && nbfcAccountDetail.getIFSC() != null ? nbfcAccountDetail.getIFSC() : "");
//        setField("branchAddressLine1", branchAddress);
//        setField("branchAddress", branchAddress);
//        setField("foreclosureAmount", "Rs." +  totalAmount);
//        setField("amountValidity", validity);
//        setField("additionalPerDayInterest", "Rs." + interestPerDay);
//        setField("customerName", dbUser.fetchUserFullName(true));
//        setField("mobileNo", dbUser.getMobileNo());
//        setField("acceptedPaymentOptions", acceptedPaymentOptions);
//        setField("demandDraft",demandDraftPayments);
//        setField("wrongPaymentOptions",wrongPaymentOptions);
//
//    }
//
//    private URL saveAndUploadToS3(String finalFileName, File finalFile) {
//        try {
//            pdDocument.save(finalFileName);
//            String bucketName = DB_Configuration.fetchTempDownloadDocBucketName();
//            System.out.println("BucketName ==> " + bucketName + "\nFileName ==> " + finalFileName);
//            HWAWSCredentials.s3Client().putObject(bucketName, "foreclosureStatements/" + finalFile.getName(), finalFile);
//            URL url = HWAWSCredentials.s3Client().getUrl(bucketName, "foreclosureStatements/" + finalFile.getName());
//            System.out.println(url.toString());
////            finalFile.delete();
//            return url;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String processBankName(String input) {
//        // Convert the input to lowercase for case-insensitive comparison
//        String lowercaseInput = input.toLowerCase();
//
//        // Check if the input contains "bank"
//        if (lowercaseInput.contains("limited")) {
//            // Remove "bank" and everything further
//            return input.substring(0, input.toLowerCase().indexOf("limited")).trim();
//        } else {
//            // Return the original input if no match is found
//            return input;
//        }
//    }
//
//    public void setField(String name, String value) {
//        try {
//            PDDocumentCatalog docCatalog = pdDocument.getDocumentCatalog();
//            PDAcroForm pdAcroForm = docCatalog.getAcroForm();
//            PDField field = pdAcroForm.getField(name);
//            if (field != null) {
//                field.setValue(value);
//                field.setReadOnly(true);
//            } else {
//                System.err.println("No field found with name:" + name);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
