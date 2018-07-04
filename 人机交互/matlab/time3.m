function [] = time3()
%TIME3 �˴���ʾ�йش˺�����ժҪ
%   �˴���ʾ��ϸ˵��
%%
clc,clear,close all
t = -5:.01:5;
%pressure = sin(10*t)';
load water.txt; %��ԭʼ���ݰ��ձ��еĸ�ʽ����ڴ��ı��ļ� water.txt
water=water';pressure=water(:);

figure
plot( pressure )
%% ѵ��������Լ��ĸ���
num_all_data = length( pressure );
% ǰ75%��������Ϊѵ������
num_train = floor( num_all_data * 0.7 );
% ��25%��������Ϊ��������
num_test = num_all_data - num_train;
% ת��Ϊnarnet��Ҫ����������
y_train_nn = num2cell( pressure(1:num_train) )';
y_test_nn = num2cell( pressure(1+num_train:end))';
%% �ӳ٣�����ǰֵ�����ڹ�ȥ�Ķ��ٸ�ֵ
feedback_delays = 1:2;
% ������ڵ�ĸ���
num_hd_neuron = 2;
% narnet����
net = narnet(feedback_delays, num_hd_neuron);
[Xs,Xi,Ai,Ts] = preparets(net,{},{}, y_train_nn);
net = train(net,Xs,Ts,Xi,Ai);
view(net)
Y = net(Xs,Xi);
perf = perform(net,Ts,Y);
fprintf( 'neural network: mse on training set : %.6f\n', perf );
%% ��������н���Ԥ��
yini = y_train_nn(end-max(feedback_delays)+1:end);
[Xs,Xi,Ai] = preparets(net,{},{},[yini y_test_nn]);
y_pred_nn = net(Xs,Xi,Ai)';
y_pred_nn = cell2mat( y_pred_nn );
y_test_nn = cell2mat( y_test_nn )';

%% ��ͼ������mse
figure
title('NARNETԤ��')
hold on
plot( y_test_nn, 'r', 'linewidth', 2 );
plot( y_pred_nn, 'b--', 'linewidth', 2 );
legend({ '��ʵֵ', '������Ԥ��ֵ'})
nn_per_error = mean(abs(y_pred_nn-y_test_nn) ./ y_test_nn);
nn_mse_error = mean( (y_pred_nn - y_test_nn).^2 );
fprintf('nn model: relative error on test set: %.6f\n', nn_per_error);
fprintf('nn model: mse on test set: %.6f\n', nn_mse_error);
end

