let sessionId;
function getReferences() {
    let url;
    document.getElementById("references").innerHTML = "";
    if (document.getElementById("filterDescription") == null)
        url = '/lab16_war_exploded/WSREF';
    else
        url = '/lab16_war_exploded/WSREF?filter=' + document.getElementById("filterDescription").value;

    fetch(url, {
        method: 'GET'
    }).then(res => {
        sessionId = res.headers.get('sessionId');
        return res.json();
    }).then(res => {
        if (res) {
            res.forEach(el => {
                let reference = `<table><tr><td>`;
                if (role === "admin")
                    reference += `
                        <input type="button" value="delete" onclick="deleteContentVisibility(${el.id})">
                        <input type="button" value="update" onclick="updateContentVisibility('${el.id}','${el.url}','${el.description}')">`

                    reference +=`
                        <input type="button" value="+${el.plus}" onclick="updateReference('${el.id}','plus', ${el.plus}, ${el.minus})">
                        <input type="button" value="-${el.minus}" onclick="updateReference('${el.id}','minus', ${el.minus}, ${el.plus})">

                        <input type="button" value="comments" onclick="getCommentForReference(${el.id})">
                        [${el.id}]
                        <a href="${el.url}">
                        ${el.description}</a>
                        </td></tr>
                        <tr><td id="${el.id}">
                        </td></tr>
                </table><br>`
                document.getElementById("references").innerHTML += reference;
            })
        }
    })
}

function addReference() {
    if (document.getElementById('url').value !== "" && document.getElementById('description').value !== "")
        fetch('/lab16_war_exploded/WSREF', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                url: document.getElementById('url').value,
                description: document.getElementById('description').value
            })
        }).then(() => {
            clearContent("form")
            getReferences();
        })
    else
        alert('Input fields must be filled')
}

function updateReference(id, recent, value, oldValue) {
    let plus;
    let minus;
    let url, description;

    console.log("plus val:" + value);
    console.log("minus val:" + oldValue);
    if (recent === "plus"){
        plus = value + 1;
        minus = oldValue;
        console.log("plus val:" + plus);
        console.log("minus val:" + minus);
    }
    else if (recent === "minus"){
        minus = value + 1;
        plus = oldValue
    }

    if (document.getElementById('editUrl') != null)
        url = document.getElementById('editUrl').value;
    if (document.getElementById('editDescription') != null)
        description =  document.getElementById('editDescription').value;

    fetch('/lab16_war_exploded/WSREF', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id, url: url, description: description, plus : plus, minus: minus
        })
    }).then(()=>{
        getReferences();
    })
}


function deleteReference(id) {
    fetch('/lab16_war_exploded/WSREF?id=' + id, {
        method: 'DELETE'
    }).then(() => {
        getReferences();
    })
}