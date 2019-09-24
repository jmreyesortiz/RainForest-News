package com.juanma.rainforestnews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {


    private static final String LOG_TAG = "NewsApp";
    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String jsonInput){
        List<News> newsList = new ArrayList<>();
        //String APi Key Appending key to URL input
        String API_KEY = "36ff0e226c7048fdab6d987813d163fb";
        String urlInput = jsonInput + API_KEY;
        //List that will have the latest News


        //Try and Catch for JSON Object creation.
        try{

            String jsonObject = "{\"status\":\"ok\",\"totalResults\":118,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Fayerwayer.com\"},\"author\":\"Diego Bastarrica\",\"title\":\"Sebastián Piñera, Macron y Harrison Ford sellan acuerdo por los bosques\",\"description\":\"En el marco de la conferencia de la ONU por el cambio climático, el presidente chileno fue flanqueado por Harrison Ford en un nuevo acuerdo. The post \\\" Sebastián Piñera, Macron y Harrison Ford sellan acuerdo por los bosques \\\" appeared first on FayerWayer.\",\"url\":\"https://www.fayerwayer.com/2019/09/sebastian-pinera-macron-harrison-ford-bosques/\",\"urlToImage\":\"https://media.metrolatam.com/2019/09/23/aton350605-a243836a0c54ef7717451b1e6e247690-1200x800.jpg\",\"publishedAt\":\"2019-09-23T13:33:00Z\",\"content\":\"Estos días Nueva York es la sede de la cumbre de la ONU por el Cambio Climático, y en la instancia el presidente chileno Sebastián Piñera junto al actor Harrison Ford, el presidente francés Emmanuel Macron y su par colombiano Iván Duque, firmaron un acuerdo p… [+1569 chars]\"},{\"source\":{\"id\":\"vice-news\",\"name\":\"Vice News\"},\"author\":\"Becky Ferreira, Jordan Pearson\",\"title\":\"Human-Caused Wildfires Caused the Sky to Change Color in Indonesia\",\"description\":\"The nation's worst fire season since 2015 spurred one Twitter user to say, \\\"This is Earth, not the planet Mars.\\\"\",\"url\":\"https://www.vice.com/en_us/article/ywab8x/human-caused-wildfires-caused-the-sky-to-change-color-in-indonesia\",\"urlToImage\":\"https://video-images.vice.com/articles/5d8904722917530008769e53/lede/1569260768999-sky.png?crop=0.8251xw:0.9766xh;0.0708xw,0.0111xh&resize=1200:*\",\"publishedAt\":\"2019-09-23T17:57:49Z\",\"content\":\"Surreal footage of a smoky red-orange haze blanketing parts of Indonesia went viral over the weekend, as the nation grapples with its worst forest fire season in years. \\r\\n This is Earth, not the planet Mars, said Twitter user @zunishofiyn in a Saturday video … [+4406 chars]\"},{\"source\":{\"id\":null,\"name\":\"Scientificamerican.com\"},\"author\":\"Oliver Milman, The Guardian\",\"title\":\"As Major Summit Convenes, U.N. Secretary-General Has Hope on Averting Warming\",\"description\":\"The summit begins just as new data shows 2014-19 was the warmest five-year period on record -- Read more on ScientificAmerican.com\",\"url\":\"https://www.scientificamerican.com/article/as-major-summit-convenes-u-n-secretary-general-has-hope-on-averting-warming/\",\"urlToImage\":\"https://static.scientificamerican.com/sciam/cache/file/D33B2993-2A8E-4645-82D1DAB73E49E345.jpg\",\"publishedAt\":\"2019-09-23T15:30:00Z\",\"content\":\"The world may have hit a hopeful “turning point” in the struggle to tackle the climate crisis despite escalating greenhouse gas emissions and the recalcitrance of major emitters Brazil and the US, according to the United Nations secretary general.\\r\\nOn Sunday,… [+5406 chars]\"},{\"source\":{\"id\":null,\"name\":\"Qz.com\"},\"author\":\"Abdi Latif Dahir\",\"title\":\"Gabon will become the first African nation to be paid to preserve its forests\",\"description\":\"Roughly 88% of Gabon's land is covered with trees.\",\"url\":\"https://qz.com/africa/1714104/gabon-to-get-150-million-from-norway-to-protect-its-forests/\",\"urlToImage\":\"https://cms.qz.com/wp-content/uploads/2019/09/AP_01070402051-e1569231275173.jpg?quality=75&strip=all&w=1400\",\"publishedAt\":\"2019-09-23T11:38:57Z\",\"content\":\"Gabon will become the first African nation to receive funding to preserve its rainforests to mitigate the effects of climate change. As part of a 10-year deal announced on Sunday (Sept. 22), Norway will pay $150 million to Gabon to battle deforestation and re… [+2396 chars]\"},{\"source\":{\"id\":null,\"name\":\"Grist.org\"},\"author\":\"Nicole Javorsky\",\"title\":\"Indigenous women traveled 3,000 miles to save an Alaskan forest from Trump\",\"description\":\"“We couldn’t be ignored.”\",\"url\":\"http://grist.org/article/indigenous-women-traveled-3000-miles-to-save-an-alaskan-forest-from-trump/\",\"urlToImage\":\"https://grist.files.wordpress.com/2019/09/20190312-delegation-wecan-dc-tongass-forest-e1569008864844.jpg?w=1200&h=675&crop=1\",\"publishedAt\":\"2019-09-23T07:00:24Z\",\"content\":\"This storywas originally published byMother Jonesand is reproduced here as part of the Climate Deskcollaboration.\\r\\nIf we had dressed in office clothing to meet the standards of D.C., says Wanda Culp, who wore vibrant red, blue, and black robes, we would have … [+7887 chars]\"},{\"source\":{\"id\":null,\"name\":\"Fastcompany.com\"},\"author\":\"Adele Peters\",\"title\":\"HP is paying $11 million to protect a New York City-size amount of forests\",\"description\":\"The company will support conservation efforts in Brazil and China, to help offset the paper that goes into the company’s printers. In a threatened stretch of rainforest along the Atlantic coast of Brazil, new restoration work will be funded by a somewhat unli…\",\"url\":\"https://www.fastcompany.com/90407784/hp-is-paying-11-million-to-protect-a-new-york-city-sized-amount-of-forests?utm_source=postup&utm_medium=email&utm_campaign=world-changing-ideas&position=1&partner=newsletter&campaign_date=09232019\",\"urlToImage\":\"https://images.fastcompany.net/image/upload/w_1280,f_auto,q_auto,fl_lossy/wp-cms/uploads/2019/09/p-1-90407784-hp-forest.jpg\",\"publishedAt\":\"2019-09-23T14:15:10Z\",\"content\":\"In a threatened stretch of rainforest along the Atlantic coast of Brazil, new restoration work will be funded by a somewhat unlikely partner—the tech manufacturer HP. The company is spending $11 million over the next five years to help the World Wildlife Fund… [+2295 chars]\"},{\"source\":{\"id\":null,\"name\":\"Firstpost.com\"},\"author\":\"Agence France-Presse\",\"title\":\"Amazon fires that sparked international outcry remain engulfed in flames as global leaders meet in New York for UN Climate Action Summit\",\"description\":\"The fires that burned through the Amazon rainforest last month sparked international outcry and offers of help, but as world leaders meet in New York, the planet's largest rainforest remains engulfed in flames The post Amazon fires that sparked international …\",\"url\":\"https://www.firstpost.com/world/amazon-fires-that-sparked-international-outcry-remain-engulfed-in-flames-as-global-leaders-meet-in-new-york-for-un-climate-action-summit-7385131.html\",\"urlToImage\":\"https://images.firstpost.com/wp-content/uploads/2019/09/1280-Amazon-.jpg\",\"publishedAt\":\"2019-09-23T03:13:21Z\",\"content\":\"Rio de Janeiro: The fires that burned through the Amazon rainforest last month sparked international outcry and offers of help, but as world leaders meet in New York, the planet's largest rainforest remains engulfed in flames.\\r\\nThe latest satellite data from … [+4009 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Aisha Salaudeen, CNN\",\"title\":\"Gabon becomes the first African country to receive funding for preserving its rainforests\",\"description\":\"Gabon recieves $150 million to preserve its rainforest, the first African country to do so\",\"url\":\"https://www.cnn.com/2019/09/23/africa/gabon-paid-to-fight-deforestation-intl/index.html\",\"urlToImage\":\"https://cdn.cnn.com/cnnnext/dam/assets/120312050736-gabon-forest-1-story-top.jpg\",\"publishedAt\":\"2019-09-23T12:26:39Z\",\"content\":null},{\"source\":{\"id\":null,\"name\":\"Theatlantic.com\"},\"author\":\"Alan Taylor\",\"title\":\"The “Forest Guardians” of Brazil’s Amazon (17 photos)\",\"description\":\"In the Amazon rainforest, on Arariboia indigenous land in Brazil’s Maranhao state, groups of Guajajara tribesmen patrol their remote tribal land, seeking to protect it from illegal logging in places where there is little police presence. They call themselves …\",\"url\":\"https://www.theatlantic.com/photo/2019/09/photos-forest-guardians-of-brazils-amazon/598645/\",\"urlToImage\":\"https://cdn.theatlantic.com/assets/media/img/photo/2019/09/forest/a01_RTX73RZX-1/facebook.jpg?1569263284\",\"publishedAt\":\"2019-09-23T18:50:44Z\",\"content\":null},{\"source\":{\"id\":null,\"name\":\"Sputniknews.com\"},\"author\":null,\"title\":\"Netizens Puzzled Over Video Allegedly Showing Red Smog Shrouding Indonesia Amid Wildfires\",\"description\":\"Fires have been burning in Indonesia since July and have already destroyed more than 800,000 hectares of rainforest, covering some parts of the country with toxic smoke. High levels of air pollution were also reported in neighbouring Malaysia and Singapore.\",\"url\":\"https://sputniknews.com/asia/201909231076870250-netizens-puzzled-over-video-allegedly-showing-red-smog-shrouding-indonesia-amid-wildfires/\",\"urlToImage\":\"https://cdn4.img.sputniknews.com/images//107686/99/1076869995.jpg\",\"publishedAt\":\"2019-09-23T14:52:44Z\",\"content\":null},{\"source\":{\"id\":null,\"name\":\"Sputniknews.com\"},\"author\":null,\"title\":\"World Leaders Attend Meeting on Amazon Rainforest as Part of UN Climate Action Summit - Video\",\"description\":\"An initiative to hold a meeting on the Amazon rainforest belongs to the permanent mission of France to the UN. The event focuses on the causes of deforestation in the Amazon region and ways to address the issue.\",\"url\":\"https://sputniknews.com/world/201909231076868800-world-leaders-attend-meeting-on-amazon-rainforest-as-part-of-un-climate-action-summit---video/\",\"urlToImage\":\"https://cdn4.img.sputniknews.com/images/107684/69/1076846920.jpg\",\"publishedAt\":\"2019-09-23T12:08:55Z\",\"content\":null},{\"source\":{\"id\":null,\"name\":\"Lewrockwell.com\"},\"author\":\"Walter E. Block\",\"title\":\"Rainforest Fires and Privatization\",\"description\":\"From: J Sent: Tuesday, August 27, 2019 10:12 PM To: wblock@loyno.edu Subject: Question about rainforest fires and privatization Hello Dr Block, I’d like to get your thoughts on the rainforest fires in Brazil and the government of Jair Bolsonaro. There has bee…\",\"url\":\"https://www.lewrockwell.com/lrc-blog/rainforest-fires-and-privatization/\",\"urlToImage\":\"https://www.lewrockwell.com/wp-content/themes/lrc/images/logo-med.png\",\"publishedAt\":\"2019-09-23T05:03:47Z\",\"content\":\"From: J\\r\\nSent: Tuesday, August 27, 2019 10:12 PM\\r\\nTo: wblock@loyno.edu\\r\\nSubject: Question about rainforest fires and privatization\\r\\nHello Dr Block,\\r\\nI’d like to get your thoughts on the rainforest fires in Brazil and the government of Jair Bolsonaro.\\r\\nThere h… [+3316 chars]\"},{\"source\":{\"id\":\"independent\",\"name\":\"Independent\"},\"author\":\"Harry Cockburn, Harry Cockburn\",\"title\":\"Deadly red haze shrouds Indonesia as rainforest burns after palm oil clearances\",\"description\":\"Government accused of failing to tackle issue after hundreds of schools in neighbouring Malaysia forced to close due to deadly smog\",\"url\":\"https://www.independent.co.uk/environment/red-haze-air-pollution-indonesia-rainforest-fires-slash-and-burn-palm-oil-climate-change-a9116696.html\",\"urlToImage\":\"https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/09/23/13/screen-shot-2019-09-23-at-13.21.28.png\",\"publishedAt\":\"2019-09-23T11:33:20Z\",\"content\":\"Fires burning since July have destroyed over 800,000 hectares of rainforest in Indonesia, blanketing the islands in thick toxic smoke which has turned the sky a violent red.\\r\\nThe gigantic conflagrations are causing respiratory problems and there are numerous … [+16826 chars]\"},{\"source\":{\"id\":null,\"name\":\"Adweek.com\"},\"author\":\"David Cohen\",\"title\":\"The MTV Video Music Awards and Its Performers Heated Up Facebook in August\",\"description\":\"MTV's Video Music Awards and VMA performers Taylor Swift, Miley Cyrus and Missy Elliott were the hottest Hot Topics on Facebook in August, according to Facebook IQ, while Instagrammers bid farewell to summer and talked up Labor Day. Star Wars also performed s…\",\"url\":\"https://www.adweek.com/digital/the-mtv-video-music-awards-and-its-performers-heated-up-facebook-in-august/\",\"urlToImage\":\"https://static.adweek.com/adweek.com-prod/wp-content/uploads/2019/09/MTVVideoMusicAwardsFBCoverImage-600x315.jpg\",\"publishedAt\":\"2019-09-23T11:00:59Z\",\"content\":\"Star Wars also performed strongly in the entertainment category on Facebook, while Eid al-Adha did so in the holidays and events category on Instagram. D23 drew conversation in the holidays and events category on Facebook, while entertainment fans on Instagra… [+1080 chars]\"},{\"source\":{\"id\":null,\"name\":\"Phys.org\"},\"author\":\"Issam Ahmed\",\"title\":\"'Moment of truth' at key UN climate summit\",\"description\":\"Some 60 world leaders convene on Monday for a UN summit on \\\"climate emergency\\\" aimed at reinvigorating the faltering Paris agreement, at a time when mankind is releasing more greenhouse gases into the atmosphere than at any time in history.\",\"url\":\"https://phys.org/news/2019-09-moment-truth-key-climate-summit.html\",\"urlToImage\":\"https://scx2.b-cdn.net/gfx/news/2019/mankindisrel.jpg\",\"publishedAt\":\"2019-09-23T07:00:01Z\",\"content\":\"Some 60 world leaders convene on Monday for a UN summit on \\\"climate emergency\\\" aimed at reinvigorating the faltering Paris agreement, at a time when mankind is releasing more greenhouse gases into the atmosphere than at any time in history.\\r\\nFrom heat waves t… [+4823 chars]\"},{\"source\":{\"id\":null,\"name\":\"Justjared.com\"},\"author\":\"Just Jared\",\"title\":\"Jennifer Lawrence Has Made Her Wedding Registry Public!\",\"description\":\"Jennifer Lawrence is getting married to art dealer Cooke Maroney and she just shared her wedding registry with fans! The 29-year-old Oscar-winning actress’ Amazon Wedding Registry guide just went live and it features all of the products she wants for hosting …\",\"url\":\"http://www.justjared.com/2019/09/23/jennifer-lawrence-has-made-her-wedding-registry-public/\",\"urlToImage\":\"http://cdn01.cdn.justjared.com/wp-content/uploads/headlines/2019/09/jlaw-registry.jpg\",\"publishedAt\":\"2019-09-23T18:24:27Z\",\"content\":\"Jennifer Lawrence is getting married to art dealer Cooke Maroney and she just shared her wedding registry with fans!\\r\\nThe 29-year-old Oscar-winning actress’ Amazon Wedding Registry guide just went live and it features all of the products she wants for hosting… [+1494 chars]\"},{\"source\":{\"id\":null,\"name\":\"Valuewalk.com\"},\"author\":\"Jacob Wolinsky\",\"title\":\"Activists call out Chase’s $196B financing of fossil fuels Since Paris Accord\",\"description\":\"Climate Activists Stage Theatrical Disruption at JPMorgan Chase Headquarters, Demanding World’s Worst Banker of Climate Change Stop financing of fossil fuels and Giving Money to... The post Activists call out Chase’s $196B financing of fossil fuels Since Pari…\",\"url\":\"https://www.valuewalk.com/2019/09/chase-financing-of-fossil-fuels/\",\"urlToImage\":\"https://d2wsh2n0xua73e.cloudfront.net/wp-content/uploads/2019/09/fossil_fuel_1568914147.jpg\",\"publishedAt\":\"2019-09-23T17:35:57Z\",\"content\":\"Climate Activists Stage Theatrical Disruption at JPMorgan Chase Headquarters, Demanding Worlds Worst Banker of Climate Change Stop financing of fossil fuels and Giving Money to Oil and Gas\\r\\n24 foot oil Slip N Slide deployed inside Chase Bank as protestors hol… [+2398 chars]\"},{\"source\":{\"id\":null,\"name\":\"Yahoo.com\"},\"author\":\"Gregory Viscusi\",\"title\":\"France Is an Amazonian Nation, Macron Says in Retort to Brazil\",\"description\":\"(Bloomberg) -- French President Emmanuel Macron further staked France’s claim to the debate over the Amazon’s future with a geography lesson -- the latest shot in a back-and-forth feud with Brazilian President Jair Bolsonaro over climate change and the destru…\",\"url\":\"https://news.yahoo.com/france-amazonian-nation-macron-says-153157000.html\",\"urlToImage\":\"https://s.yimg.com/ny/api/res/1.2/J.ObtZZ3iMRMDZa5sn6GBg--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyODA7aD05NjA-/https://s.yimg.com/uu/api/res/1.2/POMI9XpjOvvysxJ451nbLQ--~B/aD0xNTAwO3c9MjAwMDtzbT0xO2FwcGlkPXl0YWNoeW9u/https://media.zenfs.com/en/bloomberg_politics_602/afd7c6bab75f2d4706e25ec6a4c942d7\",\"publishedAt\":\"2019-09-23T20:11:15Z\",\"content\":\"(Bloomberg) -- French President Emmanuel Macron further staked Frances claim to the debate over the Amazons future with a geography lesson -- the latest shot in a back-and-forth feud with Brazilian President Jair Bolsonaro over climate change and the destruct… [+3203 chars]\"},{\"source\":{\"id\":null,\"name\":\"Project-syndicate.org\"},\"author\":\"Rémy Rioux\",\"title\":\"Reconciliation Must Drive Development\",\"description\":\"Political leaders in Russia, Brazil, the United States, and elsewhere are advancing unabashedly nationalist, nostalgia-tinged agendas in order to oppose any attempts to promote joint global governance. In doing so, they are promoting a subtle theory of withdr…\",\"url\":\"https://www.project-syndicate.org/commentary/reconciliation-must-drive-development-by-remy-rioux-2019-09\",\"urlToImage\":\"https://webapi.project-syndicate.org/library/a1ca203327416bbf5e64afb300fd1ddf.2-1-super.1.jpg\",\"publishedAt\":\"2019-09-23T10:58:13Z\",\"content\":\"Political leaders in Russia, Brazil, the United States, and elsewhere are advancing unabashedly nationalist, nostalgia-tinged agendas in order to oppose any attempts to promote joint global governance. In doing so, they are promoting a subtle theory of withdr… [+5201 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Jen Rose Smith, CNN\",\"title\":\"10 of the world's most beautiful islands\",\"description\":\"From stunning Milos in Greece to penguin hangout Bartolomé in the Galapagos, these are the world's 10 most beautiful islands.\",\"url\":\"https://www.cnn.com/travel/article/worlds-most-beautiful-islands/index.html\",\"urlToImage\":\"https://dynaimage.cdn.cnn.com/cnn/w_1200/http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F190830175752-01-worlds-most-beautiful-islands-super-tease.jpg\",\"publishedAt\":\"2019-09-23T10:00:27Z\",\"content\":\"(CNN) Spin the globe, pick a spot and odds are good you'll alight on the color blue: 71% of the Earth's surface is covered with water. \\r\\nVast stretches of ocean spread from the edges of the continents, and what lies between the coasts is true wilderness. Far … [+7588 chars]\"}]}";


            String headline = "";
            String date = "1991";
            String imageURL = "";
            String newsSource = "";
            String url = "";

            Log.i(LOG_TAG,urlInput);

            JSONObject newsObject = new JSONObject(jsonObject);
            JSONArray articles = newsObject.getJSONArray("articles");


            for(int i = 0; i<articles.length(); i++){
                JSONObject article = articles.getJSONObject(i);
                JSONObject source = article.getJSONObject("source");
                newsSource = source.getString("name");

                headline = article.getString("title");
                date = article.getString("publishedAt");
                url = article.getString("url");
                imageURL = article.getString("urlToImage");
                newsList.add(new News(headline,date,imageURL,newsSource,url));

            }


        }catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);

        }


        return newsList;
    }

    public static String httpRequest(String url) throws IOException {

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";
        String urlString = url;

        try{

            URL urlObject = new URL(urlString);
            urlConnection = (HttpURLConnection) urlObject.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

            else
            {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }



        } catch (
    MalformedURLException e) {
        e.printStackTrace();
    } catch (
    IOException e) {
        e.printStackTrace();
    }finally {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (inputStream != null) {
            // function must handle java.io.IOException here
            inputStream.close();
        }
    }

        return null;
    }


    public static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line= reader.readLine();

            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();

    }

}
