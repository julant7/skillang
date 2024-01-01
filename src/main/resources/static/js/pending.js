$(document).ready(() => {
    const userId = getCurrentUser();

    const template = $('#pending-template').html();

    renderRequests();
    function renderRequests() {
        $('#pending-ul').empty();
        getPendingRequests(userId).forEach(request => {
            const user = getUser(request.from)
            const newRequest = $(template);
            newRequest.tagName = 'li';
            newRequest.find('#name').text(`${user.firstName} ${user.lastName}`).click(() => window.location.href = `/profile?id=${request.from}`);
            newRequest.find('#nativeLanguage').addClass(`fi-${user.interestedLanguage}`);
            newRequest.find('#interestedLanguage').addClass(`fi-${user.nativeLanguage}`);
            newRequest.find('#accept').click(() => { updateFriendRequest(request.from, userId, 'accept'); renderRequests()});
            newRequest.find('#deny').click(() => { updateFriendRequest(request.from, userId, 'deny'); renderRequests()});


            $('#pending-ul').append(newRequest)
        })
    }

})