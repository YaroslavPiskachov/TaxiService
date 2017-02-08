function validate_form ( ) {
    if(document.carForm.model.value == "" ||
        document.carForm.model.value.length>40) {
        alert ( "Пожалуйста заполните корректно модель авто." );
        return false;
    }
    if(document.carForm.numberOfCar.value.length!=8){
        alert ( "Пожалуйста заполните корректно номер машини." );
        return false;
    }
    if(parseInt(document.carForm.gasolineRate.value) < 3 ||
        parseInt(document.carForm.gasolineRate.value)>30 ||
        !document.carForm.gasolineRate.value.match(/^\d+$/)) {
        alert ( "Пожалуйста заполните корректно расход авто." );
        return false;
    }
    if(parseInt(document.carForm.year.value < 1990) ||
        parseInt(document.carForm.year.value>2020)||
        !document.carForm.year.value.match(/^\d+$/)) {
        alert ( "Пожалуйста заполните корректно год производства авто." );
        return false;
    }
    return true;
}
