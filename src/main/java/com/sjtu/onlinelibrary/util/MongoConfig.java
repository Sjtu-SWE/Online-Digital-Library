package com.sjtu.onlinelibrary.util;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A class to abstract creating MongoDb Connections.
 *
 */
public class MongoConfig {

    private final MongoOptions _options = new MongoOptions();
    private List<ServerAddress> _servers;
    private String _dbName;
    private String _userName;
    private String _password;
    private static final int DEFAULT_MONGO_PORT = 27017;


    public void setConnectionsPerHost(final int a_connectionsPerHost) {
        _options.connectionsPerHost = a_connectionsPerHost;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(final int a_threadsAllowedToBlockForConnectionMultiplier) {
        _options.threadsAllowedToBlockForConnectionMultiplier = a_threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMaxWaitTime(final int a_maxWaitTime) {
        _options.maxWaitTime = a_maxWaitTime;
    }

    public void setConnectTimeout(final int a_connectTimeout) {
        _options.connectTimeout = a_connectTimeout;
    }

    public void setSocketTimeout(final int a_socketTimeout) {
        _options.socketTimeout = a_socketTimeout;
    }

    public void setAutoConnectRetry(final boolean a_autoConnectRetry) {
        _options.autoConnectRetry = a_autoConnectRetry;
    }

    public List<ServerAddress> getServers() {
        return _servers;
    }

    /**
     * Parses the list of strings. Expected format is host1:port[, host2:port]...
     *
     * @param serverList String listing all servers comma separated
     * @throws java.net.UnknownHostException thrown if the host name is no good
     */
    public void setServerList(final String serverList) throws UnknownHostException {
        if (LangUtil.isNullOrEmpty(serverList)) return;
        final StringTokenizer st = new StringTokenizer(serverList, ",");
        final List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        while (st.hasMoreTokens()) {
            final String hostAndPort = st.nextToken();
            final int colonIdx = hostAndPort.indexOf(":");
            final int port = colonIdx == -1 ? DEFAULT_MONGO_PORT : Integer.parseInt(hostAndPort.substring(colonIdx + 1));
            final String host = colonIdx == -1 ? hostAndPort : hostAndPort.substring(0, colonIdx);
            addrs.add(new ServerAddress(host, port));
        }
        setServers(addrs);
    }

    public void setServers(final List<ServerAddress> a_servers) {
        _servers = a_servers;
    }

    public String getDbName() {
        return _dbName;
    }

    public void setDbName(final String a_db) {
        _dbName = a_db;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String a_userName) {
        _userName = a_userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(final String a_password) {
        _password = a_password;
    }

    public Mongo newMongoInstance() {
        return new Mongo(_servers, _options);
    }
}


