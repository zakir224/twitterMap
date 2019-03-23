package com.test.twittermap.data.twitter;

import com.test.twittermap.crypto.signature.HmacSha1Signature;
import com.test.twittermap.util.StringUtil;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class OAuthManagerTest {

    private OAuthManager oAuthManager;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSignature() {
        try {
            String signingKey
                    = StringUtil.INSTANCE.getEncodedString("A5gUWkqel66Xm3iffjOokiYdJtQtVugiyqSCyvmOsTLsL5J03R") +
                    "&" + StringUtil.INSTANCE.getEncodedString("ATK3Q0N05o2CjCcrqQ82BTevasuJzMj5dazv8ZdiqILoy");
            oAuthManager.getSignature(signingKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSignatureBaseString() {
        try {
            String signatureBaseString = oAuthManager.getSignatureBaseString();
            System.out.print(signatureBaseString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOAuthParams() {

    }

    @Test
    public void getOAuthHeaders() {

    }
}