function getCommentForReference(id) {
    fetch('/lab16_war_exploded/WSREFComments?wsref_id=' + id, {
        method: 'GET'
    }).then(res => res.json()).then(res => {
        console.log(res);
        let data = `<table>
                    <tr><td>
                        <h2>--UWSR COMMENTS/${id}--</h2>
                        <input type="button" onclick="visibilityCommentsInsertForm(${id})" value="insert">
                        <input type="button" onclick="clearContent(${id})" value="Close comments">
                        <div id="insertComment${id}"></div>
                    </td></tr>
                </table><br>`
        if (res) {
            res.forEach(element => {
                let comments = `<table><tr><td>[${element.stamp}]`
                console.log(element.session_id);
                console.log(element.usr);
                console.log(role);
                if (role === 'admin' || element.session_id === sessionId && element.usr === role)
                    comments += `<input type="button" value="delete" onclick="deleteComment(${element.id},${element.wsref_id})">
                                 <input type="button" value="update" onclick="updateComment(${element.id},${element.wsref_id})"><br>
                                 <textarea id="txt${element.id}">${element.comment}</textarea>`
                else
                    comments += `<br><textarea readonly id="txt${element.id}">${element.comment}</textarea>`
                comments += `</td></tr></table><br>`
                data += comments;
            })
        }
        document.getElementById(id).innerHTML = data;
    })
}

function addComment(id) {
    console.log(id);
    console.log(sessionId);
    fetch('/lab16_war_exploded/WSREFComments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            wsref_id: id,
            session_id: sessionId,
            comment: document.getElementById('comment').value,
            usr: role,
        })
    }).then(() => {
        clearContent(id);
        getCommentForReference(id);
    })
}

function updateComment(id, wsref_id) {
    let needId = 'txt' + id;
    fetch('/lab16_war_exploded/WSREFComments', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            comment: document.getElementById(needId).value
        })
    }).then(() => {
        getCommentForReference(wsref_id);
    })
}

function deleteComment(id, wsref_id) {
    fetch('/lab16_war_exploded/WSREFComments?id=' + id, {
        method: 'DELETE'
    }).then(() => {
        getCommentForReference(wsref_id);
    })
}