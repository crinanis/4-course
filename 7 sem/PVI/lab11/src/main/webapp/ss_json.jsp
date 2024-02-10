<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <form>
        N: <input name="n" id="n" value="7"/><br/>
        <button type="button" onclick="getValue()">Send</button>
    </form>
    <div id="result"></div>
    <a href="<%application.getInitParameter("URL");%>unite.jsp">unite</a>
</div>
<script>
    function getValue() {
        const req = new XMLHttpRequest();

        const n = document.querySelector("#n").value;
        const res = document.querySelector("#result");

        const pattern = new RegExp(/\d+/);

        if (pattern.test(n)) {
            if (req) {
                req.open("POST", "<%application.getInitParameter("URL");%>sss_json", true);

                req.onreadystatechange = function () {
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            res.innerHTML = JSON.parse(req.response).num.join(", ");
                        } else alert("status = " +
                            req.status + "\n" + req.statusText);
                    }
                };

                req.setRequestHeader("XRand-N", n);
                req.send();
            }
        } else alert("error");
    };
</script>
</body>
</html>
