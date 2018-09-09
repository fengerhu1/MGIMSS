webpackJsonp([15],{290:function(e,t,a){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function r(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!==typeof t&&"function"!==typeof t?e:t}function o(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0});var l=a(0),i=a.n(l),s=a(5),c=a(29),u=a(60),m=a.n(u),p=function(){function e(e,t){for(var a=0;a<t.length;a++){var n=t[a];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}return function(t,a,n){return a&&e(t.prototype,a),n&&e(t,n),t}}(),d=[{id:1,status:"Pending",app_name:"app1",duration:"100min"},{id:3,status:"Running",app_name:"app3",duration:"1h"}],f=[{id:1,status:"Active",power:"400",name:"app1",mfrs:"mfrs1"},{id:3,status:"Inactive",power:"400",name:"app3",mfrs:"mfrs3"}],b=function(e){function t(e){n(this,t);var a=r(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return a.state={job:a.props.job,jobLink:"/main/schedule/"+a.props.job.id},a}return o(t,e),p(t,[{key:"render",value:function(){return i.a.createElement("tr",{key:this.state.job.id.toString()},i.a.createElement("th",{scope:"row"},i.a.createElement(c.b,{to:this.state.jobLink},this.state.job.id)),i.a.createElement("td",null,i.a.createElement(c.b,{to:this.state.jobLink},this.state.job.app_name)),i.a.createElement("td",null,this.state.job.duration),i.a.createElement("td",null,this.state.job.status),i.a.createElement("td",null,i.a.createElement(c.b,{to:this.state.jobLink},"Details")))}}]),t}(l.Component),h=function(e){function t(){n(this,t);var e=r(this,(t.__proto__||Object.getPrototypeOf(t)).call(this));return m.a.ajax({type:"GET",async:!1,url:"/schedule/get_jobs",context:document.body,success:function(e){d=m.a.parseJSON(e.toString()).data}}),m.a.ajax({type:"GET",async:!1,url:"/appliance/get_all_status",context:document.body,success:function(e){f=m.a.parseJSON(e.toString()).data}}),e}return o(t,e),p(t,[{key:"render",value:function(){var e=this;return i.a.createElement("div",{className:"animated fadeIn"},i.a.createElement(s._7,null,i.a.createElement(s.u,{xl:{size:8,offset:2}},i.a.createElement(s.i,null,i.a.createElement(s.n,null,i.a.createElement("i",{className:"fa fa-align-justify"})," Schedules"),i.a.createElement(s.j,null,i.a.createElement(s.u,{col:"6",xs:"4",sm:"2",md:"2",lg:"2",xl:"2",className:"mb-3 mb-xl-3"},i.a.createElement(y,{appdata:f})),i.a.createElement(s._10,{responsive:!0,striped:!0},i.a.createElement("thead",null,i.a.createElement("tr",null,i.a.createElement("th",{scope:"col"},"Job ID"),i.a.createElement("th",{scope:"col"},"Appliance"),i.a.createElement("th",{scope:"col"},"Duration"),i.a.createElement("th",{scope:"col"},"Status"))),i.a.createElement("tbody",null,d.map(function(t,a){return i.a.createElement(b,{key:a,job:t,reload:e.render})}))))))))}}]),t}(l.Component),y=function(e){function t(e){n(this,t);for(var a=r(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e)),o=new Date,l=o.getFullYear()+"-"+(o.getMonth()<9?"0":"")+(o.getMonth()+1)+"-"+(o.getDate()<10?"0":"")+o.getDate()+"T"+(o.getHours()<9?"0":"")+(o.getHours()+1)+":00",s=a.props.appdata,c=[],u=0;u<s.length;u++)c.push(i.a.createElement("option",null,s[u].id,"-",s[u].name));return a.state={primary:!1,option:c,time:l},a.togglePrimary=a.togglePrimary.bind(a),a.submitJob=a.submitJob.bind(a),a}return o(t,e),p(t,[{key:"togglePrimary",value:function(){this.setState({primary:!this.state.primary})}},{key:"submitJob",value:function(){var e=document.getElementById("appname").selectedIndex,t=f[e].id,a=document.getElementById("start_after").value,n=document.getElementById("finish_by").value,r=document.getElementById("duration").value;if(n<=a)return void alert('"Finish by" should be later than "Start after"');if(""===r)return void alert("Duration is required");var o=new Date(n),l=new Date(a);if(o<new Date)return void alert("Start time must be later than present!");if((o-l)/1e3/60<r)return void alert("Duration should be no longer than the time in between!");if(r<=0)return void alert("Duration must not be less than zero!");var i="Fail to send information";m.a.ajax({type:"POST",async:!1,url:"/schedule/create_job",data:{startTime:(l.getTime()/1e3).toFixed(0),lastTime:60*r,stopTime:(o.getTime()/1e3).toFixed(0),aid:t},context:document.body,success:function(e){i=e}}),alert(i),"success"===i&&window.location.reload()}},{key:"render",value:function(){return i.a.createElement("div",{className:"animated fadeIn",style:{float:"left"}},i.a.createElement(s._7,null,i.a.createElement(s.u,null,i.a.createElement(s.e,{color:"primary",onClick:this.togglePrimary,className:"mr-1 mb-3"},"New schedule"),i.a.createElement(s.Q,{isOpen:this.state.primary,toggle:this.togglePrimary,className:"modal-primary "+this.props.className},i.a.createElement(s.T,{toggle:this.togglePrimary},"Add a new job"),i.a.createElement(s.R,null,"Appliance (ID-Name) :",i.a.createElement(s.G,{id:"appname",type:"select"},this.state.option),i.a.createElement("br",null),"Start after: ",i.a.createElement(s.G,{id:"start_after",type:"datetime-local",defaultValue:this.state.time}),i.a.createElement("br",null),"Finish by: ",i.a.createElement(s.G,{id:"finish_by",type:"datetime-local",defaultValue:this.state.time}),i.a.createElement("br",null),"Duration in between (in minutes) : ",i.a.createElement(s.G,{id:"duration",type:"number",placeholder:"required"})),i.a.createElement(s.S,null,i.a.createElement(s.e,{color:"primary",onClick:this.submitJob},"Confirm")," ",i.a.createElement(s.e,{color:"secondary",onClick:this.togglePrimary},"Cancel"))))))}}]),t}(l.Component);t.default=h}});
//# sourceMappingURL=15.e25a7e25.chunk.js.map