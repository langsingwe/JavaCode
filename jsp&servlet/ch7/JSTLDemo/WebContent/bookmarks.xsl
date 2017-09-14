<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
		<html>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
			<head>
				<title>在线书签</title>
				<style> 
					th{ text-align:left; }
				</style>
			</head>
			<body>
				<h1>在线书签</h1>
				<table border="1">
					<tr>
						<th>名称</th>
						<th>网址</th>
						<th>分类</th>
					</tr>
					<xsl:for-each select="bookmarks/bookmark">
					<tr>
						<td><xsl:value-of select="title"/></td>
						<td><xsl:value-of select="url"/></td>
						<td><xsl:value-of select="category"/></td>
					</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>