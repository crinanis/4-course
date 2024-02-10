let role;

function getRole() {
    let username, password;
    if (document.getElementById('authUsername') != null)
        username = document.getElementById('authUsername').value;
    else
        username = 'client';
    if (document.getElementById('authPassword') != null)
        password = document.getElementById('authPassword').value;
    else
        password = 'password';

    fetch('/lab16_war_exploded/Users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    }).then(res => res.json()).then(res => {
        if (res) {
            if (res === "error") {
                alert("Enter correct username and password");
                role = 'client';
            } else if (res === "SignIn") {
                document.getElementById("form").innerHTML = `
                            <p>Username:<input type="text" id="authUsername"></p>
                            <p>Password:<input type="text" id="authPassword"></p>
                            <p>
                            <input type="button" onclick="getRole()" value="Sign In">
                            <input type="button" onclick="clearContent('form')" value="Cancel">
                            </p>`;
            } else {
                role = res;
                clearContent('form');
            }
            visibilityButtons();
        }
    })
}

function SignOut() {
    fetch('/lab16_war_exploded/Users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: 'reset',
            password: 'reset'
        })
    }).then(res => res.json()).then(res => {
        if (res) {
            role = res;
            visibilityButtons();
        }
    })
}

function visibilityButtons() {
    let buttons = `<input type="button" value="filter" onclick="visibilityFilterForm()">`;
    if (role === 'admin')
        buttons += `<input type="button" value="insert" onclick="visibilityInsertForm()">
                    <input type="button" value="sign out" onclick="SignOut()">`
    document.getElementById('buttons').innerHTML = buttons;
    getReferences();
}

function visibilityInsertForm() {
    document.getElementById("form").innerHTML = `
            <p>Url:<input type="text" id="url"></p>
            <p>Description:<input type="text" id="description"></p>
            <p>
                <input type="button" onclick="addReference()" value="Add Reference">
                <input type="button" onclick="clearContent('form')" value="Cancel">
            </p>`;
}

function visibilityCommentsInsertForm(id) {
    let needId = "insertComment" + id;
    document.getElementById(needId).innerHTML = `
                <p>Comment:<input type="text" id="comment"></p>
                <p>
                    <input type="button" onclick="addComment(${id})" value="Add comment">
                    <input type="button" value="Cancel" onclick="clearContent('insertComment${id}')">
                </p>`;
}

function visibilityFilterForm() {
    document.getElementById("form").innerHTML = `
                <p>Description:<input type="text" id="filterDescription"></p>
                <p>
                    <input type="button" onclick="getReferences()" value="Filter">
                    <input type="button" onclick="clearContent('form')" value="Cancel">
                </p>`;
}

function updateContentVisibility(id, url, description) {
    document.getElementById(id).innerHTML = `
                    <table>
                        <tr><td>
                            <p>Url:<input type="text" id="editUrl" value="${url}" ></p>
                            <p>Description:<input type="text" id="editDescription" value="${description}"></p>
                            <p>
                                <input type="button" onclick="updateReference(${id})" value="Update Reference">
                                <input type="button" value="Cancel" onclick="clearContent(${id})">
                            </p>
                        </td></tr>
                    </table>`;
}

function deleteContentVisibility(id) {
    document.getElementById(id).innerHTML =
                     `<table>
                        <tr><td>
                            <input type="button" value="Yes" onclick="deleteReference(${id})">
                            <input type="button" value="Cancel" onclick="clearContent(${id})">
                        </td></tr>
                     </table>`;
}

function clearContent(id) {
    document.getElementById(id).innerHTML = "";
}

