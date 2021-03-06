/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



import java.io.*;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class InstallCert {

public static void main(String[] args) throws Exception {

    String httpsUrl = null;
    String host = null;
    String proxyHost = null;
    int proxyPort = 0;
    int port = 0;
    String username = null;
    String password = null;
    char[] passphrase = "changeit".toCharArray();
    if (args.length == 0) {
    	System.out.println("Usage: java InstallCert [url] [proxyHostAddress:proxyPort] [username] [password]");
    	return;
        
    } 
    else if ((args.length == 1)  ) {
        String[] c = args[0].split(":");
        host = c[0];
        port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
    } 
    else if((args.length == 2)) {
    	String[] c = args[0].split(":");
        host = c[0];
        port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
        String[] d = args[1].split(":");
        proxyHost = d[0];
        if(d.length==2){
            proxyPort = Integer.parseInt(d[1]);
        }
        else{
        	System.out.println("Usage: java InstallCert [url] [proxyHostAddress:proxyPort] [username] [password]");
        	return;
        }
    }
    else if((args.length == 4)) {
    	String[] c = args[0].split(":");
        host = c[0];
        port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
        String[] d = args[1].split(":");
        proxyHost = d[0];
        if(d.length==2){
            proxyPort = Integer.parseInt(d[1]);
        }
        else{
        	System.out.println("Usage: java InstallCert [:port] [passphrase]");
        	return;
        }
        username = args[2];
        password = args[3];
    }
    else{
    	System.out.println("Usage: java InstallCert [:port] [passphrase]");
        return;
    }

    File file = new File("jssecacerts");
    if (file.isFile() == false) {
        char SEP = File.separatorChar;
        File dir = new File(System.getProperty("java.home") + SEP + "lib"
                + SEP + "security");
        file = new File(dir, "jssecacerts");
        if (file.isFile() == false) {
            file = new File(dir, "cacerts");
        }
    }
    System.out.println("Loading KeyStore " + file + "...");
    InputStream in = new FileInputStream(file);
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load(in, passphrase);
    in.close();

    SSLContext context = SSLContext.getInstance("TLS");
    TrustManagerFactory tmf = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(ks);
    X509TrustManager defaultTrustManager = (X509TrustManager) tmf
            .getTrustManagers()[0];
    SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
    context.init(null, new TrustManager[] { tm }, null);
    SSLSocketFactory factory = context.getSocketFactory();

    System.out.println("Opening connection to " + host + ":" + port + "...");

    URL url = new URL("https://"+host);
    HttpsURLConnection conn = null;
    final String user = username;
    final String pass = password;
    if(args.length > 1){
    	Proxy p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    	if(username!=null && password!=null){
    		Authenticator.setDefault(
         		   new Authenticator() {
         		      public PasswordAuthentication getPasswordAuthentication() {
         		         return new PasswordAuthentication(
         		               user, pass.toCharArray());
         		      }
         		   }
         		);
    	}
        
        
        conn = (HttpsURLConnection)url.openConnection(p);
    }
    else
    	conn = (HttpsURLConnection)url.openConnection();
    conn.setSSLSocketFactory(factory);
    conn.setConnectTimeout(3000); // 3 seconds.
    conn.setReadTimeout(3000); // 3 seconds
    //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.0; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
    try {
        System.out.println("Starting SSL handshake...");
        conn.connect();
        System.out.println();
        System.out.println("No errors, certificate is already trusted");
    } catch (SSLException e) {
        System.out.println();
        e.printStackTrace(System.out);
    } finally {
        conn.disconnect();
    }

    X509Certificate[] chain = tm.chain;
    if (chain == null) {
        System.out.println("Could not obtain server certificate chain");
        return;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(
            System.in));

    System.out.println();
    System.out.println("Server sent " + chain.length + " certificate(s):");
    System.out.println();
    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    for (int i = 0; i < chain.length; i++) {
        X509Certificate cert = chain[i];
        System.out.println(" " + (i + 1) + " Subject "
                + cert.getSubjectDN());
        System.out.println("   Issuer  " + cert.getIssuerDN());
        sha1.update(cert.getEncoded());
        System.out.println("   sha1    " + toHexString(sha1.digest()));
        md5.update(cert.getEncoded());
        System.out.println("   md5     " + toHexString(md5.digest()));
        System.out.println();
    }

    System.out.println("Enter certificate to add to trusted keystore or 'q' to quit: [1]");
	String line = reader.readLine().trim();
	int k;
	try {
	    k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
	} catch (NumberFormatException e) {
	    System.out.println("KeyStore not changed");
	    return;
	}

	X509Certificate cert = chain[k];
	String alias = host + "-" + (k + 1);
	ks.setCertificateEntry(alias, cert);

	OutputStream out = new FileOutputStream("jssecacerts");
	ks.store(out, passphrase);
	out.close();

	System.out.println();
	System.out.println(cert);
	System.out.println();
	System.out.println
		("Added certificate to keystore 'jssecacerts' using alias '"
		+ alias + "'");
    
}

private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

private static String toHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder(bytes.length * 3);
    for (int b : bytes) {
        b &= 0xff;
        sb.append(HEXDIGITS[b >> 4]);
        sb.append(HEXDIGITS[b & 15]);
        sb.append(' ');
    }
    return sb.toString();
}

private static class SavingTrustManager implements X509TrustManager {

    private final X509TrustManager tm;
    private X509Certificate[] chain;

    SavingTrustManager(X509TrustManager tm) {
        this.tm = tm;
    }

    public X509Certificate[] getAcceptedIssuers() {
        throw new UnsupportedOperationException();
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        throw new UnsupportedOperationException();
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        this.chain = chain;
        tm.checkServerTrusted(chain, authType);
    }
}
}
