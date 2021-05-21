# testes_integração_fase_2_5_orange
Dentro do programa de treinamento temos a necessidade de saber todas as respostas que foram dadas por uma pessoa. Para isso, temos o seguinte código:

```java
public class RespostaRepository {
   private EntityManager manager;
   
   public RespostaRepository(EntityManager manager){
        this.manager = manager;
   }
   
   public Collection<Resposta> buscaRespostas(Long idAluno){
       return manager.createQuery("select r from Resposta r where r.aluno.id =    :idAluno",Resposta.class)
                     .setParameter("idAluno",idAluno)
                     .getResultList();
   }

}
```

Descreva os testes automatizados que você vai escrever para aumentar a confiabilidade deste código.