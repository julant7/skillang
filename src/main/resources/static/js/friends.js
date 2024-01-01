$(document).ready(() => {
    const userId = getCurrentUser();

    const template = $('#friends-template').html();

    renderFriends();
    function renderFriends() {
        $('#friends-ul').empty();
        getFriends(userId).forEach(friend => {
            const user = getUser(friend)
            const newFriend = $(template);

            newFriend.find('#name').text(`${user.firstName} ${user.lastName}`).click(() => window.location.href = `/profile?id=${request.from}`);
            newFriend.find('#nativeLanguage').addClass(`fi-${user.interestedLanguage}`);
            newFriend.find('#interestedLanguage').addClass(`fi-${user.nativeLanguage}`);
            newFriend.find('#message').click(() => { window.location.href = `/messages?id=${userId},${friend}`;});
            newFriend.find('#remove').click(() => { removeFriend([userId, friend]); renderFriends()});

            $('#friends-ul').append(newFriend)
        })
    }

})