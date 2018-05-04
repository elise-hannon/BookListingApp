package com.example.android.booklistingapp;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookListParserTest {
    @Test
    public void book_parser_parses_correctly() throws Exception {
        List<Book> books = NetworkUtils.extractVolumefromJson(JSON);
        assertEquals(books.size(), 1);
        assertEquals(books.get(0).getTitle(), "Android");
    }


    private final String JSON = "{\n" +
                                " \"kind\": \"books#volumes\",\n" +
                                " \"totalItems\": 2831,\n" +
                                " \"items\": [\n" +
                                "  {\n" +
                                "   \"kind\": \"books#volume\",\n" +
                                "   \"id\": \"qKFDDAAAQBAJ\",\n" +
                                "   \"etag\": \"wO3TFkXuwV8\",\n" +
                                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/qKFDDAAAQBAJ\",\n" +
                                "   \"volumeInfo\": {\n" +
                                "    \"title\": \"Android\",\n" +
                                "    \"authors\": [\n" +
                                "     \"P.K. Dixit\"\n" +
                                "    ],\n" +
                                "    \"publisher\": \"Vikas Publishing House\",\n" +
                                "    \"publishedDate\": \"2014\",\n" +
                                "    \"description\": \"Android is a movement that has transferred data from laptop to hand-held devices like mobiles. Though there are alternate technologies that compete with Android, but it is the front runner in mobile technology by a long distance. Good knowledge in basic Java will help you to understand and develop Android technology and apps. Many universities in India and across the world are now teaching Android in their syllabus, which shows the importance of this subject. This book can be read by anyone who knows Java and XML concepts. It includes a lot of diagrams along with explanations to facilitate better understanding by students. This book aptly concludes with a project that uses Android, which will greatly benefit students in learning the practical aspects of Android. Key Features • Instructions in designing different Android user interfaces • Thorough explanations of all activities • JSON • Android-based project to aid practical understanding\",\n" +
                                "    \"industryIdentifiers\": [\n" +
                                "     {\n" +
                                "      \"type\": \"ISBN_13\",\n" +
                                "      \"identifier\": \"9789325977884\"\n" +
                                "     },\n" +
                                "     {\n" +
                                "      \"type\": \"ISBN_10\",\n" +
                                "      \"identifier\": \"9325977885\"\n" +
                                "     }\n" +
                                "    ],\n" +
                                "    \"readingModes\": {\n" +
                                "     \"text\": false,\n" +
                                "     \"image\": true\n" +
                                "    },\n" +
                                "    \"pageCount\": 372,\n" +
                                "    \"printType\": \"BOOK\",\n" +
                                "    \"categories\": [\n" +
                                "     \"Computers\"\n" +
                                "    ],\n" +
                                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                                "    \"allowAnonLogging\": false,\n" +
                                "    \"contentVersion\": \"preview-1.0.0\",\n" +
                                "    \"panelizationSummary\": {\n" +
                                "     \"containsEpubBubbles\": false,\n" +
                                "     \"containsImageBubbles\": false\n" +
                                "    },\n" +
                                "    \"imageLinks\": {\n" +
                                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=qKFDDAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                                "     \"thumbnail\": \"http://books.google.com/books/content?id=qKFDDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                                "    },\n" +
                                "    \"language\": \"en\",\n" +
                                "    \"previewLink\": \"http://books.google.com/books?id=qKFDDAAAQBAJ&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api\",\n" +
                                "    \"infoLink\": \"https://play.google.com/store/books/details?id=qKFDDAAAQBAJ&source=gbs_api\",\n" +
                                "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-qKFDDAAAQBAJ\"\n" +
                                "   },\n" +
                                "   \"saleInfo\": {\n" +
                                "    \"country\": \"US\",\n" +
                                "    \"saleability\": \"FOR_SALE\",\n" +
                                "    \"isEbook\": true,\n" +
                                "    \"listPrice\": {\n" +
                                "     \"amount\": 15.99,\n" +
                                "     \"currencyCode\": \"USD\"\n" +
                                "    },\n" +
                                "    \"retailPrice\": {\n" +
                                "     \"amount\": 9.99,\n" +
                                "     \"currencyCode\": \"USD\"\n" +
                                "    },\n" +
                                "    \"buyLink\": \"https://play.google.com/store/books/details?id=qKFDDAAAQBAJ&rdid=book-qKFDDAAAQBAJ&rdot=1&source=gbs_api\",\n" +
                                "    \"offers\": [\n" +
                                "     {\n" +
                                "      \"finskyOfferType\": 1,\n" +
                                "      \"listPrice\": {\n" +
                                "       \"amountInMicros\": 1.599E7,\n" +
                                "       \"currencyCode\": \"USD\"\n" +
                                "      },\n" +
                                "      \"retailPrice\": {\n" +
                                "       \"amountInMicros\": 9990000.0,\n" +
                                "       \"currencyCode\": \"USD\"\n" +
                                "      },\n" +
                                "      \"giftable\": true\n" +
                                "     }\n" +
                                "    ]\n" +
                                "   },\n" +
                                "   \"accessInfo\": {\n" +
                                "    \"country\": \"US\",\n" +
                                "    \"viewability\": \"PARTIAL\",\n" +
                                "    \"embeddable\": true,\n" +
                                "    \"publicDomain\": false,\n" +
                                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                                "    \"epub\": {\n" +
                                "     \"isAvailable\": false\n" +
                                "    },\n" +
                                "    \"pdf\": {\n" +
                                "     \"isAvailable\": true,\n" +
                                "     \"acsTokenLink\": \"http://books.google.com/books/download/Android-sample-pdf.acsm?id=qKFDDAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                                "    },\n" +
                                "    \"webReaderLink\": \"http://play.google.com/books/reader?id=qKFDDAAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
                                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                                "    \"quoteSharingAllowed\": false\n" +
                                "   },\n" +
                                "   \"searchInfo\": {\n" +
                                "    \"textSnippet\": \"Many universities in India and across the world are now teaching Android in their syllabus, which shows the importance of this subject. This book can be read by anyone who knows Java and XML concepts.\"\n" +
                                "   }\n" +
                                "  }\n" +
                                " ]\n" +
                                "}";
}