package org.yh.yhframe.adapter.rv;

import org.yh.library.adapter.rv.YhRecyclerAdapter;
import org.yh.yhframe.bean.MenuModel;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////
/**
  * MyRecyclerAdatpter
  * @author y
  * @time 2017/5/22 11:45
  */
public class MyRecyclerAdatpter extends YhRecyclerAdapter<MenuModel>
{
    public MyRecyclerAdatpter()
    {
        addItemViewDelegate(new RvMsgComingItemDelagate());
        addItemViewDelegate(new RVMsgSendItemDelagate());
    }
}
