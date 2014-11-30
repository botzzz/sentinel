<!DOCTYPE html>
<html>
<head>
<title>Supervision</title>
<link href="scripts/jtable.2.4.0/themes/metro/red/jtable.css" rel="stylesheet" type="text/css" />
<link href="scripts/jquery-ui-1.11.2/jquery-ui.theme.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="scripts/syntaxhighlighter/styles/shCoreDefault.css" type="text/css"/>

<script src="scripts/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="scripts/jquery-ui-1.11.2/jquery-ui.min.js"	type="text/javascript"></script>
<script src="scripts/jtable.2.4.0/jquery.jtable.min.js"	type="text/javascript"></script>
<script src="scripts/syntaxhighlighter/scripts/shCore.js" type="text/javascript" ></script>
<script src="scripts/syntaxhighlighter/scripts/shBrushJava.js" type="text/javascript" ></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#SentinelTableContainer').jtable({
			title : 'Sentinel report list',
			paging : true, //Enable paging
			actions : {
				listAction : 'controller?action=list',
			},
			fields : {
				id : {
					key: true,
					title : 'ID',
					width : '5%',
					list : true,
					sort: true
				},
				name : {
					title : 'Name',
					width : '10%'
				},
				source : {
					title : 'Source',
					width : '10%'
				},
				destination : {
					title : 'Destination',
					width : '10%'
				},
				loggedDate : {
					title : 'Date',
					width : '10%'
				},
				status : {
					title : 'Status',
					width : '10%',
					display: function (data) {
						if(data.record.status == 'SUCCESS'){
		                	return '<label style="color:white; background-color:#86c440; padding:2px; display:block; width:100px; margin:auto;">' + data.record.status+ '</label>';
						} else {
		                	return '<label style="color:white; background-color:#c44040; padding:2px; display:block; width:100px; margin:auto;">' + data.record.status+ '</label>';
						}
		           }
				},
				messageOrigine : {
					key: true,
					title : 'Details',
					width : '5%',
					display: function (sentinelData) {
                        var $img = $('<img src="images/envelope.png" title="Watch message" />');
                        $img.click(function () {
                            $('#SentinelTableContainer').jtable('openChildTable',
                                    $img.closest('tr'),
                                    {
                                        title: 'Détail n°'+sentinelData.record.id,
                                        actions: {
                                            listAction: 'controller?action=getDetails&id=' + sentinelData.record.id
                                        },
                            			fields : {
                            				messageOrigine : {
                            					title: 'Message',
                            					width: '50%'
                            				},
                            				stackTrace : {
                            					title: 'StackTrace',
                            					width: '50%',
                            					display: function (data) {
                            					 	setTimeout(function() { SyntaxHighlighter.highlight(); }, 2000);
                            						return '<pre id="codeContent" class="brush:java">' + sentinelData.record.stackTrace + "</pre>";
                            		           }
                            				}
                            			}
                                    }, 
                                    function (data) {
                                    	 data.childTable.jtable('load');
                                    }
                       		);
						});
                        return $img;
					}
				}
			}
		});
		$('#SentinelTableContainer').jtable('load');
	});
</script>

</head>

<body>
	<div style="text-align: center;">
		<div id="SentinelTableContainer"></div>
	</div>
</body>
</html>