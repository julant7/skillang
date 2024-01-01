$(document).ready(() => {
    const userId = getCurrentUser();
    let params = (new URL(window.location)).searchParams;
    let id = params.get("id");
    const messageFromTemplate = $('#message-from').html();
    const messageToTemplate = $('#message-to').html();
    const chatbox = $('#chat-box');
    if (id) {
        $('#chats').attr('hidden', 'true');
        $('#chat').removeAttr('hidden');
        const messages = getMessages(`[${id}]`);
        let recipient;
        console.log(id.split(',')[1])
        if (id.includes(',')) {
            recipient = id.split(',')[1];
        }
        if (messages)
            Object.keys(messages).forEach(key => {
                let newMessage;
                if (messages[key].author == userId) {
                    recipient = messages[key].recipient;
                    newMessage = $(messageToTemplate);
                    newMessage.find('#msg-p-to').text(messages[key].content);
                }
                else {
                    recipient = messages[key].author;
                    newMessage = $(messageFromTemplate);
                    newMessage.find('#msg-p-from').text(messages[key].content);
                }
                chatbox.append(newMessage)
            })
        chatbox.stop().animate({
            scrollTop: chatbox.prop("scrollHeight")
        }, 700);

        $('#message-form').off();
        $('#message-form').on('submit', (event) => {
            event.preventDefault();
            sendMessage(userId, recipient, $('#message-field').val());
            let newMessage = $(messageToTemplate);
            newMessage.find('#msg-p-to').text($('#message-field').val());
            chatbox.append(newMessage)
            chatbox.stop().animate({
                scrollTop: chatbox.prop("scrollHeight")
            }, 700);
            $('#message-field').val(' ');
        })
    } else {
        const template = $('#chat-template').html();
        $('#messages-ul').empty();
        let chats = getChatIds(userId);
        chats.forEach(chatId => {
            let chat = getChat(JSON.stringify(chatId), userId);
            const newChat = $(template);
            newChat.find('#chat-div').click(() => window.location.href = `/messages?id=${chatId}`);
            newChat.find('#name').text(chat.name);
            newChat.find('#lastMessage').text(chat.content)
            $('#messages-ul').append(newChat)
        });
    }

})