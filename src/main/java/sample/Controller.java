package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Controller {

    private static final String apiKey = "trnsl.1.1.20150805T161736Z.610303e9db2452b1.fbbc75c6b898e4de0670b8066fde4404192beb65";

    static String createUrl(){
     /*   System.out.println("https://translate.yandex.net/api/v1.5/tr.json/translate?key="+apiKey+
                "&text=" + View.ta.getText() +
                "&lang=" + View.fromLangCB.getValue().toString().substring(0,2) + "-" +
                View.toLangCB.getValue().toString().substring(0,2));*/
        return  "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+apiKey+
                "&text=" + View.ta.getText() +
                "&lang=" + View.fromLangCB.getValue().toString().substring(0,2) + "-" +
                View.toLangCB.getValue().toString().substring(0,2);

    }

    static String getTranslation(){
        StringBuilder result = new StringBuilder();
        String translation = "";
        try {
            String textLink = createUrl();
            URL link = new URL(textLink) ;
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(link.openStream(),"UTF-8"));

            while (br.ready()) {
                result.append((char)br.read());
            }
            System.out.println(result);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(result.toString());
            JSONObject jsonObj = (JSONObject) obj;
            translation = jsonObj.get("text").toString();
            System.out.println(translation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return translation.substring(2,translation.length()-2);
    }

    static void addLangs(ComboBox cb){
        String tempo =
                "af:Африканский ar:Арабский az:Азербайджанский be:Белорусский bg:Болгарский bs:Боснийский " +
                        "ca:Каталанский cs:Чешский cy:Валлийский da:Датский de:Немецкий el:Греческий en:Английский " +
                        "es:Испанский et:Эстонский eu:Баскский fa:Персидский fi:Финский fr:Французский ga:Ирландский " +
                        "gl:Галисийский he:Иврит hr:Хорватский ht:Гаитянский hu:Венгерский hy:Армянский " +
                        "id:Индонезийский is:Исландский it:Итальянский ja:Японский ka:Грузинский kk:Казахский " +
                        "ko:Корейский ky:Киргизский la:Латынь lt:Литовский lv:Латышский mg:Малагасийский " +
                        "mk:Македонский mn:Монгольский ms:Малайский mt:Мальтийский nl:Голландский no:Норвежский " +
                        "pl:Польский pt:Португальский ro:Румынский ru:Русский sk:Словацкий sl:Словенский sq:Албанский " +
                        "sr:Сербский sv:Шведский sw:Суахили tg:Таджикский th:Тайский tl:Тагальский tr:Турецкий " +
                        "tt:Татарский uk:Украинский uz:Узбекский vi:Вьетнамский zh:Китайский"
                ;
        ObservableList languages = FXCollections.observableArrayList();
        String parts[] = tempo.split(" ");
        for (int i = 0; i < parts.length-1; i++) {
            languages.add(parts[i]);
     //       System.out.println(parts[i]);
        }
        cb.getItems().addAll(languages);
    }


}

