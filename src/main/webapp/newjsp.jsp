<%-- 
    Document   : newjsp
    Created on : 5 d�c. 2018, 13:57:18
    Author     : pedago
--%>

<!DOCTYPE html>

<html>
 <title>Edition des taux de remise (AJAX)</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- On charge jQuery -->
<link href="/jtable/themes/metro/blue/jtable.min.css" rel="stylesheet" type="text/css" />
 
 <script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <!-- On charge le moteur de template mustache https://mustache.github.io/ -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
        <script>
            $(document).ready(// Ex�cut� � la fin du chargement de la page
                    function () {
                        // On montre la liste des codes
                        showCodes();

                    }
            );

            function showCodes() {
                // On fait un appel AJAX pour chercher les codes
                $.ajax({
                    url: "allCodes",
                    dataType: "json",
                    error: showError,
                    success: // La fonction qui traite les r�sultats
                            function (result) {
                                // Le code source du template est dans la page
                                var template = $('#codesTemplate').html();
                                
                                var chartData = [];
                                // On met le descriptif des donn�es
                                chartData.push(["Produit", "chiffre d'affaires"]);
                                for(var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                console.log(chartData);
                                // On combine le template avec le r�sultat de la requ�te
                                var processedTemplate = Mustache.to_html(template, chartData);
                                // On affiche la liste des options dans le select
                                $('#codes').html(processedTemplate);
                               
                                
                            }
                });
            }

            // Ajouter un code
            function addCode(Code) {
                $.ajax({
                    url: "addCode",
                    // serialize() renvoie tous les param�tres saisis dans le formulaire
                    data: $("#codeForm").serialize(),
                    dataType: "json",
                    success: // La fonction qui traite les r�sultats
                            function (result) {
                                showCodes();
                                console.log(result);
                            },
                    error: showError
                });
                return false;
            }

            // Supprimer un code
            function deleteCode(code) {
                $.ajax({
                    url: "deleteCode",
                    data: {"code": code},
                    dataType: "json",
                    success: 
                            function (result) {
                                showCodes();
                                console.log(result);
                            },
                    error: showError
                });
                return false;
            }

            // Fonction qui traite les erreurs de la requ�te
            function showError(xhr, status, message) {
                alert(JSON.parse(xhr.responseText).message);
            }

        </script>
<body>
<a href='allCodes' target="_blank">Voir les donn�es brutes</a><br>
<h1>Edition des taux de remise (AJAX)</h1>
        �<!-- La zone o� les r�sultats vont s'afficher -->
        <div id="codes">
           
        </div>
        <!-- Le template qui sert � formatter la liste des codes -->
        <script id="codesTemplate" type="text/template">
            <TABLE>
            <tr><th>Numero</th><th>Customer_id</th><th>Product_id</th><th>Quantity</th><th>Shipping_cost</th><th>Sales_date</th><th>Shipping_date</th><th>freight_company</th></tr>
            {{! Pour chaque enregistrement }}
            {{#records}}
                {{! Une ligne dans la table }}
                <TR><TD>{{order_num}}</TD><TD>{{records.customer_id}}</TD><TD>{{records.product_id}}</TD><TD>{{records.quantity}}</TD><TD>{{records.shipping_cost}}</TD><TD>{{records.sales_date}}</TD><TD>{{records.shipping_date}}</TD><TD>{{records.freight_company}}</TD><TD><button onclick="addCode('{{order_num}}')">Commander</button></TD></TR>
            {{/records}}
            </TABLE>
        </script>


</body>
</html>
