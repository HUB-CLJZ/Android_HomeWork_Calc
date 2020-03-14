function top_slide_left_click(btn,obj){
	var slide_length = obj.children("li").length;
	var li_width = obj.children("li").width();//Get width with li
	//get current li
	var current_li_id = parseInt($(btn).parent().find(".active").attr("attr-id"));
	//obj.find(".img-" + current_li_id).animate({"left":-(li_width/2)+"px"});
	obj.find(".img-" + current_li_id).removeClass("active");
	//console.log("current_li_id="+(current_li_id));
	//if active is the first
	if(current_li_id == 1){
		for(var j = current_li_id,i=2;j<=slide_length,i<=slide_length-current_li_id+2;j++,i++){
			//console.log("j="+j+" i=" +i);
			if(j==slide_length-1){
				//console.log(".img-" + j+" width:" + -(li_width/2))
				obj.find(".img-" + j).animate({"left":-(li_width/2)+"px"});
			}else if(j==slide_length){
				//console.log(".img-" + j+" width:" + (li_width/2+20));
				obj.find(".img-" + j).addClass("active");
				obj.find(".img-" + j).animate({"left":(li_width/2+20)+"px"})
			}else{
				//console.log(".img-" + j+" width:" + (li_width/2+li_width*(i-1)+20*i))
				obj.find(".img-" + j).animate({"left":(li_width/2+li_width*(i-1)+20*i)+"px"});
			}
		}
	}else{
		for(var j = current_li_id,i=2;j<=slide_length,i<=slide_length-current_li_id+2;j++,i++){
			obj.find(".img-" + j).animate({"left":(li_width/2+li_width*(i-1)+20*i)+"px"});
		}
	}
	
	for(var j = current_li_id-1,i=1;j>1,i<current_li_id;j--,i++){
		//console.log("2-"+j);
		//第一个就是当前
		if(j == current_li_id-1){
			//console.log(".img-" + j+" width:" + (li_width/2+20));
			obj.find(".img-" + j).addClass("active");
			obj.find(".img-" + j).animate({"left":(li_width/2+20)+"px"})
			if(current_li_id==2){
				obj.find(".img-" + slide_length).animate({"left":-(li_width/2)+"px"});
			}
		}else{
			if(j == current_li_id-2){
				//console.log("previous= .img-" + j+" width:" + -(li_width/2))
				obj.find(".img-" + j).animate({"left":-(li_width/2)+"px"});
			}else{
				//console.log(".img-" + j+" width:" + (li_width/2+li_width*(i-1)+20*i))
				obj.find(".img-" + j).animate({"left":(li_width/2+li_width*(i-1)+20*i)+"px"});
			}
			
		}
	}
}

function top_slide_right_click(btn,obj){
	var slide_length = obj.children("li").length;
	var li_width = obj.children("li").width();//Get width with li
	//get current li
	//alert(slide_length);
	var current_li_id = parseInt($(btn).parent().find(".active").attr("attr-id"));
	obj.find(".img-" + current_li_id).removeClass("active");
	//console.log("current_li_id="+(current_li_id));
	//if active is the first
	if(current_li_id == 1){
		for(var j = current_li_id,i=-1;j<=slide_length,i<=slide_length-current_li_id-1;j++,i++){
			//console.log("j="+j+" i=" +i);
			if(j==1){
				//console.log(".img-" + j+" width:" + -(li_width/2))
				obj.find(".img-" + j).animate({"left":-(li_width/2)+"px"});
			}else if(j==current_li_id+1){
				//console.log(".img-" + j+" width:" + (li_width/2+20));
				obj.find(".img-" + j).addClass("active");
				obj.find(".img-" + j).animate({"left":(li_width/2+20)+"px"});
			}else{
				//console.log(".img-" + j+" width:" + (li_width/2+li_width*(i)+20*(i+1)))
				obj.find(".img-" + j).animate({"left":(li_width/2+li_width*(i)+20*(i+1))+"px"});
			}
		}
	}else{
		for(var j = current_li_id,i=-1;j<=slide_length,i<=slide_length-current_li_id-1;j++,i++){
			if(current_li_id+1<=slide_length){
				if(j == current_li_id){
					obj.find(".img-" + j).animate({"left":-(li_width/2)+"px"});
				}else if(j==current_li_id+1){
					obj.find(".img-" + j).addClass("active");
					obj.find(".img-" + j).animate({"left":(li_width/2+20)+"px"});
					//console.log(j);
				}else{
					obj.find(".img-" + j).animate({"left":(li_width/2+li_width*(i)+20*(i+1))+"px"});
				}
			}else{
				if(j == slide_length){
					//console.log(".img-" + j+" width:" + (li_width/2+20));
					obj.find(".img-" + j).animate({"left":-(li_width/2)+"px"});
					obj.find(".img-1").addClass("active");
					//obj.find(".img-" + 1).animate({"left":(li_width/2+20)+"px"})
					//console.log("last");
				}else{
					obj.find(".img-" + j).animate({"left":(li_width/2+li_width*(i)+20*(i+1))+"px"});
				}
			}
			
			//console.log("j---1="+j)
		}
	}
	
	
	for(var j = current_li_id-1,i=1;j>=1,i<current_li_id;j--,i++){
		//console.log("j = "+j+ " i=" +i + " current_li_id="+current_li_id);
		if(current_li_id==slide_length){
			if(j == 1){
				obj.find(".img-" + j).animate({"left":(li_width/2+20)+"px"});
			}else{
				if(j==2){
					obj.find(".img-" + j).animate({"left":(li_width/2*(j+1)+20*2)+"px"});
				}else{
					obj.find(".img-" + j).animate({"left":-(li_width/2*(j+1)+20*(i))+"px"});
				}
				//obj.find(".img-" + j).css("left",-(li_width/2*(j+1)+20*(i)));
				//console.log(".img-" + j+" width:" + -(li_width/2*(j+2)+20*(i)))
				//console.log(".img-" + j+" width:" + (li_width/2+li_width*(i)+20*(i+1)))
			}
			//console.log(".img-" + j+" width:" + (li_width/2+li_width*(i)+20*(i+1)))
		}else{
			if(current_li_id==slide_length-1){
				if(j == 1){
					obj.find(".img-" + j).animate({"left":(li_width/2*(j+2)+20*(j+1))+"px"});
					//console.log(".img-" + j+" width:" + (li_width/2*(j+2)+20*(j+1)));
				}else{
					obj.find(".img-" + j).animate({"left":-(li_width/2*(i+2)+20*(i))+"px"});
					//console.log(".img-" + j+" width:" + -(li_width/2*(i+2)+20*(i)))
				}
			}else{
				obj.find(".img-" + j).animate({"left":-(li_width/2*(i+2)+20*(i))+"px"});
				//console.log(".img-" + j+" width:" + -(li_width/2*(i+2)+20*(i)))
			}
		}
	}
}
