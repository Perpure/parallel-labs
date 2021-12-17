package ru.bmstu.iu9;

import java.util.ArrayList;

public class ServersMessage {
    private final ArrayList<String> servers;

    public ArrayList<String> getServers() {
        return servers;
    }

    public ServersMessage(ArrayList<String> servers) {
        this.servers = servers;
    }
}
