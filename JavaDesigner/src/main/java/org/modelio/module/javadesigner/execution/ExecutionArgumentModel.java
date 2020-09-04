package org.modelio.module.javadesigner.execution;


public class ExecutionArgumentModel {
    private String arguments = "";

    private String vmArguments = "";

    public ExecutionArgumentModel(final String arguments, final String vmArguments) {
        this.arguments = arguments;
        this.vmArguments = vmArguments;
    }

    public void setArguments(final String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return this.arguments;
    }

    public void setVmArguments(final String vmArguments) {
        this.vmArguments = vmArguments;
    }

    public String getVmArguments() {
        return this.vmArguments;
    }

}
