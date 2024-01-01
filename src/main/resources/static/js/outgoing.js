$(document).ready(() => {
    const userId = getCurrentUser();

    const template = $('#outgoing-template').html();

    renderRequests();
    function renderRequests() {
        $('#outgoing-ul').empty();
        getOutgoingRequests(userId).forEach(request => {
            const user = getUser(request.to)
            const newRequest = $(template);
            newRequest.find('#name').text(`${user.firstName} ${user.lastName}`).click(() => window.location.href = `/profile?id=${request.from}`);
            newRequest.find('#nativeLanguage').addClass(`fi-${user.interestedLanguage}`);
            newRequest.find('#interestedLanguage').addClass(`fi-${user.nativeLanguage}`);
            newRequest.find('#deny').click(() => { updateFriendRequest(userId, request.to, 'deny'); renderRequests()});
            $('#outgoing-ul').append(newRequest)
        })
    }

})