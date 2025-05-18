package com.newsportal.backend.configuration;

import com.newsportal.backend.entity.Finance;
import com.newsportal.backend.entity.News;
import com.newsportal.backend.entity.Weather;
import com.newsportal.backend.service.FinanceService;
import com.newsportal.backend.service.NewsService;
import com.newsportal.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final NewsService newsService;
    private final WeatherService weatherService;
    private final FinanceService financeService;

    // Backend URL'si - production'da değiştirilmeli
    private final String BASE_URL = "http://localhost:8081";

    // Placeholder image URL'leri için temel URL
    private final String PLACEHOLDER_BASE = "https://placehold.co";

    @Autowired
    public DataInitializer(NewsService newsService, WeatherService weatherService, FinanceService financeService) {
        this.newsService = newsService;
        this.weatherService = weatherService;
        this.financeService = financeService;
    }

    // Add this method at the beginning of the class to check if tables exist
    private boolean tablesExist() {
        try {
            // Check if news table has data
            return !newsService.getAllNews().isEmpty();
        } catch (Exception e) {
            // If there's an error (like table doesn't exist), return false
            return false;
        }
    }

    // Then modify the run method to check if tables exist first
    @Override
    public void run(String... args) {
        // Only initialize if tables are empty
        if (tablesExist()) {
            System.out.println("Database already initialized, skipping data initialization");
            return;
        }

        // Check if data already exists
        if (newsService.getAllNews().isEmpty()) {
            initializeNewsData();
        }

        if (weatherService.getWeatherByCity("Istanbul").isEmpty()) {
            initializeWeatherData();
        }

        if (financeService.getAllFinanceData().isEmpty()) {
            initializeFinanceData();
        }
    }

    private void initializeNewsData() {
        List<News> newsList = Arrays.asList(
                new News(
                        "Yerli Otomobil Togg Avrupa'da Satışa Çıkıyor",
                        "Türkiye'nin yerli otomobili Togg, 2025 yılında Avrupa pazarına giriş yapacak. Şirket yetkilileri, Avrupa'daki satış stratejisi hakkında detaylı bilgi verdi.\n\nTogg CEO'su, \"Avrupa pazarı bizim için çok önemli. Elektrikli araçlara olan talep her geçen gün artıyor ve biz de bu pazarda yerimizi almak istiyoruz\" açıklamasında bulundu. İlk etapta Almanya, Fransa ve İtalya'da satışa sunulacak olan Togg, ilerleyen dönemlerde diğer Avrupa ülkelerinde de yerini alacak.",
                        "https://ares.shiftdelete.net/2023/11/togg-avrupa-birligi-satis-tarihi-kapak.jpg",
                        "Ekonomi",
                        "Ali Yılmaz",
                        "16 Mayıs 2025"
                ),

                new News(
                        "Yeni Eğitim Reformu Meclisten Geçti",
                        "Milli Eğitim Bakanlığı'nın sunduğu reform paketi TBMM'de onaylandı. Reform paketi, eğitim sisteminde köklü değişiklikler içeriyor.\n\nYeni sistemde, öğrencilerin dijital yetkinliklerinin artırılması ve yabancı dil eğitiminin güçlendirilmesi hedefleniyor. Ayrıca, mesleki eğitime daha fazla önem verilecek ve staj imkanları artırılacak. Reform paketinin 2025-2026 eğitim öğretim yılında uygulamaya konulması planlanıyor.",
                        "https://iaahbr.tmgrup.com.tr/e30c83/660/310/0/68/800/444?u=https://iahbr.tmgrup.com.tr/2024/10/11/9-yargi-paketi-neler-getiriyor-9-yargi-paketi-meclisten-gecti-mi-ne-zaman-cikacak-iste-ayrintilar-1728640232145.jpeg",
                        "Eğitim",
                        "Elif Demir",
                        "15 Mayıs 2025"
                ),

                new News(
                        "Meteor Yağmuru Bu Gece Türkiye'den İzlenebilecek",
                        "Uzmanlar, gece saatlerinde gökyüzünde görsel şölen yaşanacağını söylüyor. Perseid meteor yağmuru, bu gece Türkiye'den çıplak gözle izlenebilecek.\n\nAstronomi uzmanları, meteor yağmurunun özellikle gece yarısından sonra daha net görülebileceğini belirtiyor. Işık kirliliğinin az olduğu bölgelerde saatte yaklaşık 100 meteor görülebileceği tahmin ediliyor. Gökyüzü meraklıları için bu doğa olayı kaçırılmayacak bir fırsat olarak değerlendiriliyor.",
                        "https://samsungazetesicom.teimg.com/crop/1280x720/samsungazetesi-com/uploads/2023/08/meteor-yagmuru-ne-zaman-saat-kacta-408.jpg",
                        "Bilim",
                        "Burak Yıldız",
                        "14 Mayıs 2025"
                ),

                new News(
                        "Dolar Rekor Seviyeye Ulaştı",
                        "Piyasalarda dalgalanma devam ederken dolar 33 TL'yi gördü. Ekonomistler, küresel ekonomideki belirsizliklerin döviz kurlarını etkilediğini belirtiyor.\n\nMerkez Bankası'ndan yapılan açıklamada, piyasalardaki dalgalanmanın geçici olduğu ve gerekli önlemlerin alınacağı ifade edildi. Uzmanlar ise yatırımcılara temkinli olmaları tavsiyesinde bulunuyor. Önümüzdeki hafta açıklanacak enflasyon verilerinin piyasalar üzerinde etkili olması bekleniyor.",
                        "https://www.inegolunsesi.com/resimler/2025-2/3/74285572626780.webp",
                        "Ekonomi",
                        "Mehmet Demir",
                        "13 Mayıs 2025"
                ),

                new News(
                        "Yeni Marvel Filmi Vizyona Girdi",
                        "Hayranların heyecanla beklediği yeni Marvel filmi sinemalarda yerini aldı. Film, ilk gösterim gününde gişe rekoru kırdı.\n\nÜnlü yönetmenin imzasını taşıyan film, özel efektleri ve etkileyici oyunculuk performanslarıyla dikkat çekiyor. Eleştirmenler, filmin serinin en iyi yapımlarından biri olduğunu belirtiyor. Filmin başrolünde yer alan oyuncu, röportajında \"Bu projenin bir parçası olmaktan gurur duyuyorum\" ifadelerini kullandı.",
                        "https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/3421528F5E3679CEA7D89FE51BE6DE6904289364AD148688A2E236A340144BF6/scale?width=1200&aspectRatio=1.78&format=webp",
                        "Magazin",
                        "Zeynep Kaya",
                        "12 Mayıs 2025"
                ),

                new News(
                        "İstanbul'da Deprem Tatbikatı Gerçekleştirildi",
                        "Afet ve Acil Durum Yönetimi Başkanlığı tarafından geniş çaplı bir deprem tatbikatı düzenlendi. Tatbikata binlerce vatandaş ve yüzlerce kurtarma ekibi katıldı.\n\nTatbikat kapsamında, deprem anında yapılması gerekenler ve tahliye prosedürleri uygulamalı olarak gösterildi. Yetkililer, İstanbul'un depreme hazırlık durumunun her geçen gün iyileştiğini, ancak vatandaşların da bireysel hazırlıklarını tamamlaması gerektiğini vurguladı.",
                        "https://i.ytimg.com/vi/On1a1mcqCS4/maxresdefault.jpg",
                        "Dünya",
                        "Ayşe Yıldız",
                        "11 Mayıs 2025"
                ),

                new News(
                        "Sağlık Bakanlığı Yeni Diyabet İlacını Onayladı",
                        "Yeni ilaç, Tip 2 diyabet hastaları için umut vadeden bir çözüm olabilir. Klinik deneylerde başarılı sonuçlar elde edildi.\n\nUzmanlar, ilacın kan şekerini dengelemede etkili olduğunu ve yan etkilerinin minimal düzeyde olduğunu belirtiyor. İlacın önümüzdeki ay eczanelerde satışa sunulması planlanıyor. Sağlık Bakanlığı yetkilileri, ilacın SGK kapsamında karşılanacağını açıkladı.",
                        "https://cdn1.ntv.com.tr/gorsel/x7t4Ez9P60KMoEolTFUu7Q.jpg?width=952&height=540&mode=both&scale=both",
                        "Sağlık",
                        "Deniz Şahin",
                        "10 Mayıs 2025"
                ),

                new News(
                        "Futbol Ligi'nde Heyecan Devam Ediyor",
                        "Süper Lig'de bu hafta oynanan derbi maç büyük çekişmeye sahne oldu. Karşılaşma 2-2'lik beraberlikle sonuçlandı.\n\nMaçın ilk yarısında ev sahibi takım 2-0 öne geçse de, ikinci yarıda konuk takım skoru eşitlemeyi başardı. Karşılaşmanın son dakikalarında her iki takım da galibiyet golü için baskı kursa da, skor değişmedi. Bu sonuçla birlikte lig lideri değişmedi, ancak puan farkı azaldı.",
                        "https://trthaberstatic.cdn.wp.trt.com.tr/resimler/1788000/avrupanin-5-buyuk-liginde-gorunum-1789078.jpg",
                        "Spor",
                        "Can Özkan",
                        "9 Mayıs 2025"
                ),

                new News(
                        "Yapay Zeka ile Kanser Teşhisinde Yeni Dönem",
                        "Araştırmacılar, yapay zeka destekli tanı sistemlerinin doğruluk oranını artırdığını açıkladı. Sistem, erken teşhis konusunda umut vadediyor.\n\nGeliştirilen yapay zeka algoritması, radyoloji görüntülerini analiz ederek kanser belirtilerini yüksek doğrulukla tespit edebiliyor. Uzmanlar, bu teknolojinin özellikle erken evrede teşhisi zor olan kanser türleri için önemli bir gelişme olduğunu vurguluyor. Sistemin önümüzdeki yıl hastanelerde kullanılmaya başlanması planlanıyor.",
                        "https://www.sehirmedya.com/static/2025/05/11/yapay-zeka-ile-kanser-teshisinde-yeni-donem-1746967798-202_small.jpg",
                        "Bilim",
                        "Ahmet Kara",
                        "8 Mayıs 2025"
                ),

                new News(
                        "Turizmde Sezon Umut Vadediyor",
                        "Otellerdeki rezervasyonlar geçen yıla göre %40 artış gösterdi. Turizm sektörü temsilcileri, bu yılın rekor bir sezon olacağını öngörüyor.\n\nÖzellikle Akdeniz ve Ege bölgelerindeki tatil beldeleri yoğun ilgi görüyor. Yabancı turist sayısında da önemli bir artış bekleniyor. Turizm Bakanlığı, bu yıl Türkiye'yi ziyaret edecek turist sayısının 60 milyonu aşacağını tahmin ediyor.",
                        "https://www.muglameydan.com.tr/images/haberler/2025/04/mugla-da-turizm-sezonu-umut-vadediyor-5465-t.jpg",
                        "Ekonomi",
                        "Selin Yılmaz",
                        "7 Mayıs 2025"
                )
        );

        newsService.saveAllNews(newsList);
    }

    private void initializeWeatherData() {
        // Istanbul weather
        List<Weather> istanbulWeather = Arrays.asList(
                new Weather(null, "16 Mayıs", 24.0, "Sunny", "sun", "Istanbul"),
                new Weather(null, "17 Mayıs", 22.0, "Partly Cloudy", "cloud-sun", "Istanbul"),
                new Weather(null, "18 Mayıs", 19.0, "Rainy", "cloud-rain", "Istanbul"),
                new Weather(null, "19 Mayıs", 21.0, "Cloudy", "cloud", "Istanbul"),
                new Weather(null, "20 Mayıs", 23.0, "Sunny", "sun", "Istanbul")
        );

        // Ankara weather
        List<Weather> ankaraWeather = Arrays.asList(
                new Weather(null, "16 Mayıs", 22.0, "Sunny", "sun", "Ankara"),
                new Weather(null, "17 Mayıs", 20.0, "Partly Cloudy", "cloud-sun", "Ankara"),
                new Weather(null, "18 Mayıs", 17.0, "Rainy", "cloud-rain", "Ankara"),
                new Weather(null, "19 Mayıs", 19.0, "Cloudy", "cloud", "Ankara"),
                new Weather(null, "20 Mayıs", 21.0, "Sunny", "sun", "Ankara")
        );
        // izmir weather
        List<Weather> izmirWeather = Arrays.asList(
                new Weather(null, "16 Mayıs", 26.0, "Sunny", "sun", "izmir"),
                new Weather(null, "17 Mayıs", 24.0, "Partly Cloudy", "cloud-sun", "izmir"),
                new Weather(null, "18 Mayıs", 21.0, "Rainy", "cloud-rain", "izmir"),
                new Weather(null, "19 Mayıs", 23.0, "Cloudy", "cloud", "izmir"),
                new Weather(null, "20 Mayıs", 25.0, "Sunny", "sun", "izmir")
        );
        // Antalya weather
        List<Weather> AntalyaWeather = Arrays.asList(
                new Weather(null, "16 Mayıs", 28.0, "Sunny", "sun", "Antalya"),
                new Weather(null, "17 Mayıs", 26.0, "Partly Cloudy", "cloud-sun", "Antalya"),
                new Weather(null, "18 Mayıs", 23.0, "Rainy", "cloud-rain", "Antalya"),
                new Weather(null, "19 Mayıs", 25.0, "Cloudy", "cloud", "Antalya"),
                new Weather(null, "20 Mayıs", 27.0, "Sunny", "sun", "Antalya")
        );
        // Bursa weather
        List<Weather> BursaWeather = Arrays.asList(
                new Weather(null, "16 Mayıs", 23.0, "Sunny", "sun", "Bursa"),
                new Weather(null, "17 Mayıs", 21.0, "Partly Cloudy", "cloud-sun", "Bursa"),
                new Weather(null, "18 Mayıs", 18.0, "Rainy", "cloud-rain", "Bursa"),
                new Weather(null, "19 Mayıs", 20.0, "Cloudy", "cloud", "Bursa"),
                new Weather(null, "20 Mayıs", 22.0, "Sunny", "sun", "Bursa")
        );
        weatherService.saveAllWeather(istanbulWeather);
        weatherService.saveAllWeather(ankaraWeather);
        weatherService.saveAllWeather(izmirWeather);
        weatherService.saveAllWeather(AntalyaWeather);
        weatherService.saveAllWeather(BursaWeather);
    }

    private void initializeFinanceData() {
        List<Finance> financeList = Arrays.asList(
                new Finance(null, "DOLAR", 37.98, 0.18, 0.48),
                new Finance(null, "EURO", 41.68, -0.23, -0.55),
                new Finance(null, "STERLİN", 49.01, -1.63, -3.32),
                new Finance(null, "BİTCOİN", 82671.78, 1234.56, 1.51),
                new Finance(null, "BİST 100", 9379.83, -102.34, -1.09),
                new Finance(null, "ALTIN", 2710.58, -62.27, -2.3),
                new Finance(null, "FAİZ", 60.00, 0.23, 0.5)
        );

        financeService.saveAllFinance(financeList);
    }
}
