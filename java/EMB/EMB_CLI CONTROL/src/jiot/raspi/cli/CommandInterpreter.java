/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.cli;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import jiot.raspi.things.ControlPoint;
import jiot.raspi.things.ControlPointContainer;
import jiot.raspi.things.FloatInputSupport;
import jiot.raspi.things.OutputControlPoint;

/**
 *
 * @author yjkim
 */
public class CommandInterpreter {
    
    private static final AtomicReference<CommandInterpreter> instance =
            new AtomicReference<CommandInterpreter>();
    
    public static CommandInterpreter getInstance() {
        if (instance.get() == null)
            instance.set(new CommandInterpreter());
        return instance.get();
    }
    
    public interface Command {
        public String execute(String[] args);
        public String getHelp();
    }
    
    private final Map<String, Command> commands = new HashMap<String, Command>();
    
    private CommandInterpreter() {
        commands.put("list", new Command() {
            @Override
            public String execute(String[] args) {
                StringBuilder sb = new StringBuilder();
                Collection<ControlPoint> cps = 
                    ControlPointContainer.getInstance().getControlPoints();
                sb.append("ControlPointContainer has " + cps.size() + "'s control points.")
                  .append(System.lineSeparator());
                cps.forEach((cp) -> { 
                    sb.append(cp.toString()).append(System.lineSeparator());
                });
                return sb.toString();
            }

            @Override
            public String getHelp() {
                return "list: display a list of control points in the container";
            }
        });
        commands.put("get", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 2) {
                    return "Invalid get command.";
                } 
                else {
                    int pointId = Integer.parseInt(args[1]);
                    ControlPoint cp = ControlPointContainer.getInstance().getControlPoint(pointId);
                    if (cp == null) {
                        return "Cannot find the control point(id=" + pointId + ").";
                    }
                    else {
                        return String.valueOf(cp instanceof FloatInputSupport ? 
                                ((FloatInputSupport)cp).getFloatValue() : cp.getPresentValue());
                    }
                }
            }

            @Override
            public String getHelp() {
                return "get: get the present value of a control point. usage => get point-id";
            }
        });
        commands.put("set", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 3) {
                    return "Invalid set command.";
                } 
                else {
                    int pointId = Integer.parseInt(args[1]);
                    ControlPoint cp = ControlPointContainer.getInstance().getControlPoint(pointId);
                    if (cp == null) {
                        return "Cannot find the control point(id=" + pointId + ").";
                    }
                    else if (cp instanceof OutputControlPoint) {
                        int value = Integer.parseInt(args[2]);
                        ((OutputControlPoint)cp).setPresentValue(value);
                        return null;
                    }
                    else {
                        return "The control point(id=" + pointId + ") is not an output type.";
                    }
                }
            }

            @Override
            public String getHelp() {
                return "set: set the present value of a control point. usage => set point-id value";
            }
        });
        commands.put("rename", new Command() {
            @Override
            public String execute(String[] args) {
                if (args.length != 3) {
                    return "Invalid set command.";
                } 
                else {
                    int pointId = Integer.parseInt(args[1]);
                    ControlPoint cp = ControlPointContainer.getInstance().getControlPoint(pointId);
                    if (cp == null) {
                        return "Cannot find the control point(id=" + pointId + ").";
                    }
                    else {
                        cp.setName(args[2]);
                        return null;
                    }
                }
            }

            @Override
            public String getHelp() {
                return "rename: change the name of a control point. usage => rename point-id new-name";
            }
        });
    }
    
    public String help() {
        StringBuilder sb = new StringBuilder();
        sb.append("IoT CLI Commands").append(System.lineSeparator());
        commands.values().forEach((command) -> { 
            sb.append(command.getHelp()).append(System.lineSeparator());
        });
        return sb.toString();
    }
    
    public String execute(String[] args) {
        String response = null;

        if (args.length != 0) {
            if (args[0].equals("help")) {
                response = help();
            }
            else {
                Command cmd = commands.get(args[0]);
                if (cmd != null) {
                    response = cmd.execute(args);
                }
                else {
                    response = "Invalid command: " + args[0];
                }
            }
        }

        return response;
    }
    
    public void register(String name, Command cmd) {
        commands.put(name, cmd);
    }
    
    public void unregister(String name) {
        commands.remove(name);
    }
    
}
