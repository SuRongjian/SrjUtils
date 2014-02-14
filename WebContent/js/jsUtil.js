/**
 * 
 */
 
 //
 function checkFunctionarguments(fun){
 	var message='';
 	var args=fun.arguments;    //arguments是javascript对象的属性，任何typeof为function的对象都有该属性
 	message+='arguments个数:'+args.length+'<br>';
 	for(var i=0;i<args.length;i++){
 		var oType=typeof(args[i]);
 		
 		if(oType=='object'){
 			message+='arguments['+i+']&nbsp;typeof:'+oType
 			try{
 				var extjsXtype=args[i].xtype;
 				message+='&nbsp;extXtype:'+extjsXtype;
 			}catch(e){
 				//这个try-catch块是用来判断extjs对象的类型的，如果该对象不是ext对象，则没这个项输出
 			}
 			message+='<br>';
 		}else if(oType=='string'||oType=='boolean'||oType=='number'){
 			message+='arguments['+i+']&nbsp;typeof:'+oType+'&nbsp;value:'+args[i];
 		}else{
 			message+='arguments['+i+']&nbsp;typeof:'+oType+'&nbsp;<br>';
 		}
 		
 	}
 	
 }
 
 