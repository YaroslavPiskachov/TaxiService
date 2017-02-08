function validate_form ( ) {
    if(document.driverForm.fullName.value == "" ||
        document.driverForm.fullName.value.length>40) {
        alert ( "Пожалуйста заполните корректно Фамилию Имя Отчество." );
        return false;
    }
    if(document.driverForm.mobNumber.value.length!=10 ||
        !document.driverForm.mobNumber.value.match(/^\d+$/)){
        alert ( "Пожалуйста заполните корректно номер телефона." );
        return false;
    }

    if(document.driverForm.expiriance.value=="" ||
        parseInt(document.driverForm.expiriance.value)<1||
        document.driverForm.expiriance.value.length>4 ||
        !document.driverForm.expiriance.value.match(/^\d+$/) ){
        alert ( "Пожалуйста заполните корректно опыт вождения авто." );
        return false;
    }
    return true;
}
