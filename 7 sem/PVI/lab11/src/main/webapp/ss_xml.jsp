<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>ss_xml</title>
</head>
<body>
<div class="wrapper">
    <form>
        N: <input name="n" id="n" value="3"/><br/>
        <button type="button" onclick="getValue()">Send</button>
    </form>
    <div id="result"></div>
    <a href="<%application.getInitParameter("URL");%>ss_json.jsp">ss_json</a>
</div>
<script>
    function getValue() {
        const req = new XMLHttpRequest();

        const n = document.querySelector("#n").value;
        const res = document.querySelector("#result");

        const pattern = new RegExp(/\d+/);

        if (pattern.test(n)) {
            if (req) {
                req.open("POST", "<%application.getInitParameter("URL");%>sss_xml", true);
                req.onreadystatechange = function () {
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            console.log(req.responseXML);
                            res.innerHTML = Array.from(req.responseXML.getElementsByTagName("num"))
                                                                        .map(item => item.textContent).join(", ");
                        } else alert("status = " +
                            req.status + "\n" + req.statusText);
                    }
                };

                req.setRequestHeader("XRand-N", n);
                req.send();
            }
        } else alert("error");
    }
</script>
</body>
</html>
