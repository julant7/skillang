$(document).ready(() => {
    const form = $('#registration-form');

    form.on('submit', (event) => {
        event.preventDefault();
        if (!form[0].checkValidity()) {
            event.stopPropagation();
        } else {
            let data = new FormData(form[0])
            const body = {};
            data.forEach((value, key) => body[key] = value);

            if (register(body))
                window.location.href = '/login';
        }

        form[0].classList.add('was-validated')
    })
})

