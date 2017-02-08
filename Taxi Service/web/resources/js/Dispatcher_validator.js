function validate_form ( ) {
    if(document.dispatcherForm.fullName.value == "" ||
        document.dispatcherForm.fullName.value.length>40) {
        alert ( "Пожалуйста заполните корректно Фамилию Имя Отчество." );
        return false;
    }
    if(document.dispatcherForm.age.value.length>3 ||
        !document.dispatcherForm.age.value.match(/^\d+$/)){
        alert ( "Пожалуйста заполните корректно возраст." );
        return false;
    }
    return true;
}

