package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;

/**
 * 运行时常量池
 */
public class RuntimeConstantPool {

    private List<ConstantEntry> constants = new ArrayList<>();

    public RuntimeConstantPool(ConstantPool pool) {
        BaseConstantInfo[] infos = pool.getConstantInfos();
        for (BaseConstantInfo info : infos) {
            constants.add(createConstantEntry(info));
        }
    }

    public ConstantEntry getConstantEntryByIndex(int index) {
        return constants.get(index);
    }

    private ConstantEntry createConstantEntry(BaseConstantInfo constantInfo) {
//        ConstantEntry constant = new ConstantEntry();
//        switch (constantInfo.tag) {
//            case ConstantPoolTag.CONSTANT_TAG_UTF8: {
//                return
//            }
//            case ConstantPoolTag.CONSTANT_TAG_INTEGER: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_FLOAT: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_LONG: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_DOUBLE: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_CLASS: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_STRING: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_FIELDREF: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_METHODREF: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_INTERFACEMETHODREF: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_NAMEANDTYPE: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_METHODHANDLE: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_METHODTYPE: {
//
//            }
//            case ConstantPoolTag.CONSTANT_TAG_INVOKEDYNAMIC: {
//
//            }
//            default:
//                break;
//        }
//        return constant;
        return null;
    }
}
