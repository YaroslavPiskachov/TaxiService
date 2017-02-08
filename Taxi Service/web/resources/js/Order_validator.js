function validate_form ( ) {
    if(document.orderForm.addressDeparture.value == "" ||
        document.orderForm.addressDeparture.value.length>40) {
        alert ( "Пожалуйста заполните корректно адресс отправления." );
        return false;
    }
    if(document.orderForm.addressArrive.value == "" ||
        document.orderForm.addressArrive.value.length>40) {
        alert ( "Пожалуйста заполните корректно адресс прибытия." );
        return false;
    }

    if(!document.orderForm.countPassangers.value.match(/^\d+$/)||
        parseInt(document.orderForm.countPassangers.value) < 0 ||
        parseInt(document.orderForm.countPassangers.value) > 7) {
        alert ( "Пожалуйста заполните корректно количество пассажиров." );
        return false;
    }

    if(document.orderForm.distance.value=="" ||
        parseFloat(document.orderForm.distance.value)<3 ||
        !document.orderForm.distance.value.match(/^\d+$/) ||
        parseFloat(document.orderForm.distance.value)>150){

        alert ( "Пожалуйста заполните корректно дистанцию." );
        return false;
    }
    return true;
}
