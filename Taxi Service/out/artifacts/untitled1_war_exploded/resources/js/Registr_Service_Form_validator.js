/**
 * Created by Yaroslav on 05.01.2017.
 */
function validate_form ( ) {
    if(document.regForm.city.value == "" ||
        document.regForm.city.value.length>20) {
        alert ( "Пожалуйста заполните корректно поле 'Город'." );
        return false;
    }
    if(document.regForm.adress.value == "" ||
        document.regForm.adress.value.length>40){
        alert ( "Пожалуйста заполните корректно поле 'Адресс'." );
        return false;
    }
    if(document.regForm.phoneNumber.value=="" ||
        document.regForm.phoneNumber.value.length>11 ||
        !document.regForm.phoneNumber.value.match(/^\d+$/) ){
        alert ( "Пожалуйста заполните корректно поле 'Номер телефона'." );
        return false;
    }
    return true;
}