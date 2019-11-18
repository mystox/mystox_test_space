package tech.mystox.test.hadoop.rpc.entity;


import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by mystoxlol on 2019/1/18, 13:19.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ParamEntityTest implements WritableComparable<ParamEntityTest>
{
    public static final long versionID=11L;
    public static final long serialVersionUID = -2438964352392225398L;

    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ParamEntityTest{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(ParamEntityTest o)
    {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException
    {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException
    {

    }

    /*@Override
    public String toString()
    {
        return "ParamEntityTest{" +
                "name='" + name + '\'' +
                '}';
    }*/
}
