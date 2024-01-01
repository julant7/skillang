$(document).ready(() => {
    const form = $('#login-form');

    form.on('submit', (event) => {
        event.preventDefault();
        let data = new FormData(form[0])
        const body = {};
        data.forEach((value, key) => body[key] = value);

        const auth = login(body);
        if (auth !== -1) {
            localStorage.setItem('currentId', auth.toString());
            window.location.href = '/profile';
        } else {
            $('#wrong-password').removeAttr('hidden');
        }
    })
})