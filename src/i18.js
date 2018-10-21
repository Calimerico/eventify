/**
 * Created by spasoje on 22-Oct-18.
 */
import i18n from "i18next";
import {reactI18nextModule} from "react-i18next";

// the translations
// (tip move them in a JSON file and import them)
const resources = {
    en: {
        translation: {
            "navbar.aboutUs": "About us",
            "navbar.careers": "Careers",
            "navbar.home": "Home",
            "navbar.signIn": "Sign in",
            "navbar.register": "Register",
            "events.eventName": "Event name",
            "events.price": "Price",
            "events.date": "Date",
            "events.time": "Time",
            "events.place": "Place"
        }
    },
    sr: {
        translation: {
            "navbar.aboutUs": "O nama",
            "navbar.careers": "Karijera",
            "navbar.home": "Pocetna",
            "navbar.signIn": "Prijavi se",
            "navbar.register": "Registruj se",
            "events.eventName": "Naziv dogadjaja",
            "events.price": "Cena",
            "events.date": "Datum",
            "events.time": "Vreme",
            "events.place": "Mesto"
        }
    }
};

i18n
    .use(reactI18nextModule) // passes i18n down to react-i18next
    .init({
        resources,
        lng: "en",

        keySeparator: false,
        interpolation: {
            escapeValue: false
        }
    })
;

export default i18n;