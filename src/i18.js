/**
 * Created by spasoje on 22-Oct-18.
 */
import i18n from "i18next";
import {reactI18nextModule} from "react-i18next";
import translationEN from './resources/locales/en/translation.json';
import translationSR from './resources/locales/sr/translation.json';

// the translations
// (tip move them in a JSON file and import them)
const resources = {
    en: {
        translation: translationEN
    },
    sr: {
        translation: translationSR
    }
};
//TODO Auto check language somehow!
i18n
    .use(reactI18nextModule) // passes i18n down to react-i18next
    .init({
        resources,
        lng: "en",
        fallbackLng: "en",
        keySeparator: true,
        interpolation: {
            escapeValue: false
        }
    })
;

export default i18n;