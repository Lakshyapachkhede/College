<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/students">
        <html>
            <body>
                <h2>Student List</h2>
                <table style="border: 1px solid black;
  border-collapse: collapse;">

                    <tr>
                        <th
                            style="border: 1px solid black;
                        border-collapse: collapse;">
        Name</th>
                        <th style="border: 1px solid black;
  border-collapse: collapse;">Branch</th>
                        <th style="border: 1px solid black;
  border-collapse: collapse;">Semester</th>
                    </tr>


                    <xsl:for-each select="student">
                        <tr>
                            <td style="border: 1px solid black;
  border-collapse: collapse;">
                                <xsl:value-of select="name" />
                            </td>
                            <td style="border: 1px solid black;
  border-collapse: collapse;">
                                <xsl:value-of select="branch" />
                            </td>
                            <td style="border: 1px solid black;
  border-collapse: collapse;">
                                <xsl:value-of select="sem" />
                            </td>

                        </tr>


                    </xsl:for-each>

                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>