/**
 * Created by Yaroslav on 05.01.2017.
 */
function LoginValidate_form ( ) {
    if (document.logInForm.j_username.value == "") {
        alert("Пожалуйста заполните поле 'ФИО'.");
        return false;
    }
    if (document.logInForm.j_password.value == "") {
        alert("Пожалуйста заполните поле 'Пароль'.");
        return false;
    }
    return true;
}

