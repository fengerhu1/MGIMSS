clc,clear
load water.txt; %��ԭʼ���ݰ��ձ��еĸ�ʽ����ڴ��ı��ļ� water.txt
water=water';x=water(:)';
s=12; %���� s=12
n=12; %Ԥ�����ݵĸ���
m1=length(x); %ԭʼ���ݵĸ���
for i=s+1:m1
 y(i-s)=x(i)-x(i-s);
end
m2=length(y); %���ڲ�ֺ����ݵĸ���
w=diff(y); %���������ԵĲ������
m3=length(w); %�������ղ�ֺ����ݵĸ���
for i=0:3
 for j=0:s+1
 %spec= garchset('R',i,'M',j,'Display','off'); %ָ��ģ�͵Ľṹ
 spec = garch(i,j);
 EstMdl = estimate(spec, w);
 
 V = infer(EstMdl,xdata); %V = conditional variances, also n x 1
 xvol = sqrt(V);
 %[coeffX,errorsX,LLFX] = garchfit(spec,w); %��ϲ���
 num=garchcount(coeffX); %������ϲ����ĸ���
 %compute Akaike and Bayesian Information Criteria
 [aic,bic]=aicbic(LLFX,num,m3);
 fprintf('R=%d,M=%d,AIC=%f,BIC=%f\n',i,j,aic,bic); %��ʾ������
 end
end
save bdata x y w n m1 m2 s 