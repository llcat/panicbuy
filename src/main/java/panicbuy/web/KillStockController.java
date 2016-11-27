package panicbuy.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import panicbuy.dto.ControllerResult;
import panicbuy.dto.Exposer;
import panicbuy.dto.KillExecutionInfo;
import panicbuy.entity.Stock;
import panicbuy.exception.KillClosedException;
import panicbuy.exception.RepeatKillException;
import panicbuy.service.KillInfoEnum;
import panicbuy.service.KillStockService;
import panicbuy.service.impl.KillStockServiceImpl;

@Controller
@RequestMapping("")
public class KillStockController {
	@Autowired
	private KillStockService kss;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// model+view(list.jsp) = ModelAndView
		List<Stock> list = kss.getStockList();
		model.addAttribute("list", list);
		return "list";  //返回list.jsp,配置的ViewReslover会将数据绑到相应页面并返回给客户端
	}
	
	@RequestMapping(value="/{stockId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("stockId")Long stockId,Model model){
		if(stockId==null){
			return "redirect:/panicbuy/list";
		}
		Stock stock = kss.getById(stockId);
		if(stock==null){
			return "forward:/panicbuy/list";
		}
		model.addAttribute("stock", stock);
		return "detail";
	}
	
	@RequestMapping(value="/{stockId}/exposer",method=RequestMethod.POST
			       ,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public ControllerResult<Exposer> exposer(@PathVariable("stockId")Long stockId){
		ControllerResult<Exposer> result;
		try{
		Exposer exposer = kss.exposeStartUrl(stockId);
		result = new ControllerResult<Exposer>(true, exposer);
		}catch(Exception e){
			result = new ControllerResult<Exposer>(false, e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/{stockId}/{md5}/execute",method=RequestMethod.POST
		       ,produces={"application/json;charset=utf-8"})
    @ResponseBody
    public ControllerResult<KillExecutionInfo> execute(
    		          @PathVariable("stockId")Long stockId,
    		          @CookieValue("userPhone")Long userPhone,
    		           @PathVariable("md5")String md5){
		if(userPhone == null){
			return new ControllerResult<KillExecutionInfo>(false,"未注册");
		}
		try{
		KillExecutionInfo execution = kss.executeKill(stockId, userPhone, md5);
		return new ControllerResult<KillExecutionInfo>(true, execution);
		}catch(RepeatKillException e1){
			KillExecutionInfo execution = new KillExecutionInfo(stockId,KillInfoEnum.REREAT_KILL);
			return new ControllerResult<KillExecutionInfo>(false, execution);
		}
		catch(KillClosedException e2){
			KillExecutionInfo execution = new KillExecutionInfo(stockId,KillInfoEnum.END);
			return new ControllerResult<KillExecutionInfo>(false, execution);
		}
		catch(Exception e){
			KillExecutionInfo execution = new KillExecutionInfo(stockId,KillInfoEnum.INNER_ERROR);
			return new ControllerResult<KillExecutionInfo>(false, execution);
		}
	}
	@RequestMapping(value="/time/now",method=RequestMethod.GET,
					produces={"application/json;charset=utf-8"})
	@ResponseBody
	public ControllerResult<Long> time(){
		Date now = new Date();
		return new ControllerResult<Long>(true, now.getTime());
	}
}
