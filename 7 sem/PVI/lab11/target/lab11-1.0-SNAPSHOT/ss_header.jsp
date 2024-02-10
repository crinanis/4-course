<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>sss_header</title>
</head>
<body>
<div class="wrapper">
    <div>
        <form action="/sss_header">
            X: <input name="x-value" id="x-value" value="1"/><br/>
            Y: <input name="y-value" id="y-value" value="5"/><br/>
            <button type="button" onclick="getValue()">Send</button>
        </form>
    </div>
    <div id="result"></div>
    <a href="<%application.getInitParameter("URL");%>ss_xml.jsp">ss_xml</a>
    <a href="<%application.getInitParameter("URL");%>unite.jsp">unite</a>
</div>
<script>
    function getValue() {
        const req = new XMLHttpRequest();

        const x = document.querySelector("#x-value").value;
        const y = document.querySelector("#y-value").value;
        const z = document.querySelector("#result");

        const pattern = new RegExp(/\d+/);

        if (pattern.test(x) && pattern.test(y)) {
            if (req) {
                req.open("POST", "<%application.getInitParameter("URL");%>sss_header", true);

                req.onreadystatechange = function () {
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            z.innerHTML = req.getResponseHeader("Value-Z");
                        } else alert("status = " +
                            req.status + "\n" + req.statusText);
                    }
                };

                req.setRequestHeader("Value-X", x);
                req.setRequestHeader("Value-Y", y);

                req.send();
            }
        } else alert("error");
    }
</script>
</body>
</html>
