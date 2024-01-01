$(document).ready(() => {
    let userId = getCurrentUser();
    let user = getUser(userId);

    if (!user) window.location.href = '/login';

    $('#card-name').text(`${user.firstName} ${user.lastName}`);
});