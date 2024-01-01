function setFriendRequest(userId, id) {
    const addFriend = $('#add-friend');
    addFriend.off();
    switch(getFriendStatus(userId, id)) {
        case true:
            addFriend.text('Cancel request');
            addFriend.click(() => {
                updateFriendRequest(userId, id, 'toggle');
                setFriendRequest(userId, id);
            });
            break;
        case false:
            addFriend.text('Add friend');
            addFriend.click(() => {
                updateFriendRequest(userId, id, 'toggle');
                setFriendRequest(userId, id);
            });
            break;
        case 'pending':
            addFriend.text('Accept');
            addFriend.click(() => {
                updateFriendRequest(id, userId, 'accept');
                setFriendRequest(id, userId);
            })
            break;
        case 'friend':
            addFriend.hide();
            $('#about-div').attr('class', 'card-body p-4 text-black mt-2');
            break;

    }
}
$(document).ready(() => {
    let params = (new URL(window.location)).searchParams;
    let id = params.get("id");
    let userId = getCurrentUser();
    if (id === userId) window.location.href = '/profile';
    let user;
    if (id) {
        user = getUser(id);
        $('#edit-profile').attr('disabled', 'true');
        setFriendRequest(userId, id);
    }
    else {
        user = getUser(userId);
        $('#add-friend').hide();
        $('#about-div').attr('class', 'card-body p-4 text-black mt-2');
    }

    if (!window.location.pathname.includes('edit')) {
        $('#name').text(`${user.firstName} ${user.lastName}`);
        $('#age').text(user.age);
        $('#bio').text(user.bio);
        $('#nativeLanguage').addClass(`fi-${user.nativeLanguage}`);
        $('#interestedLanguage').addClass(`fi-${user.interestedLanguage}`);
    }
    else {
        $('#firstName').val(user.firstName).focus();
        $('#lastName').val(user.lastName).focus();
        $('#age').val(user.age).focus();
        $('#bio').val(user.bio).focus();
        $('#nativeLanguage').val(user.nativeLanguage);
        $('#interestedLanguage').val(user.interestedLanguage);

        const editForm = $('#edit-form');
        editForm.on('submit', (event) => {
            event.preventDefault();
            if (!editForm[0].checkValidity()) {
                event.stopPropagation();
            } else {
                let data = new FormData(editForm[0])
                const body = {};
                data.forEach((value, key) => body[key] = value);

                setUser(userId, body);
                window.location.href = '/profile';
            }
        })

    }

});