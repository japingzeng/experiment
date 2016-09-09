package experiment.designmodle.visitorM;

/** 
 * @ClassName: Visitor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月9日 下午6:54:40 
 *  
 */  
public class Visitor implements IVisitor<Element> {

	public  void visit(Element cel) {
		cel.dosomgthing();
	}

}
