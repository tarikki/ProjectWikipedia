package testers;

import main.Translator;

/**
 * Created by extradikke on 24/04/15.
 */
public class TranslatorTester {

    public static void main(String[] args) {

        Translator translator = new Translator();
        int yolo = translator.getNumberFromName("2014 Winter Olympics");
        System.out.println(yolo);
        System.out.println(translator.getNameFromNumber(5981421));
    }
}
