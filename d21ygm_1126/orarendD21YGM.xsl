<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes" />
    <xsl:template match="/">
        <html>
            <head>
                <title>Órarend</title>
            </head>
            <body>
                <h1>Órarend</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Óra ID</th>
                            <th>Típus</th>
                            <th>Tantárgy</th>
                            <th>Nap</th>
                            <th>Tól</th>
                            <th>Ig</th>
                            <th>Helyszín</th>
                            <th>Oktató</th>
                            <th>Szak</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="XY:orarend/ora">
                            <tr>
                                <td><xsl:value-of select="@id" /></td>
                                <td><xsl:value-of select="@tipus" /></td>
                                <td><xsl:value-of select="targy" /></td>
                                <td><xsl:value-of select="idopont/nap" /></td>
                                <td><xsl:value-of select="idopont/tol" /></td>
                                <td><xsl:value-of select="idopont/ig" /></td>
                                <td><xsl:value-of select="helyszin" /></td>
                                <td><xsl:value-of select="oktato" /></td>
                                <td><xsl:value-of select="szak" /></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
