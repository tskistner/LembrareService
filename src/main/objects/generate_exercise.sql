delimiter //

drop procedure if exists generate_exercise; //
create procedure generate_exercise (in id_usuario_p int, 
									in id_exercicio_p int, 
									out ds_exercicio_p text, 
									out ds_resposta_p text)
begin

declare qt_iterator_w int(10);

	/*Questões pessoais*/
	if (id_exercicio_p = 1) then 
		begin
		call generate_personal_information(id_usuario_p, ds_exercicio_p, ds_resposta_p);
		end;
	/*Identificação de objetos/formas*/
	elseif (id_exercicio_p = 3 or id_exercicio_p = 2) then
		begin
		call generate_object(ds_exercicio_p,ds_resposta_p);
		end;
	/*Eventos*/
	elseif (id_exercicio_p = 7) then 
		begin
		call generate_events(id_usuario_p, ds_exercicio_p, ds_resposta_p);		
		end;
	end if;
   
end; //