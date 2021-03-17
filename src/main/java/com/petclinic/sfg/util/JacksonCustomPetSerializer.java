package com.petclinic.sfg.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.petclinic.sfg.model.Owner;
import com.petclinic.sfg.model.Pet;
import com.petclinic.sfg.model.PetType;
import com.petclinic.sfg.model.Visit;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class JacksonCustomPetSerializer extends StdSerializer<Pet> {

	public JacksonCustomPetSerializer() {
		this(null);
	}

	protected JacksonCustomPetSerializer(Class<Pet> t) {
		super(t);
	}

	@Override
	public void serialize(Pet pet, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		jgen.writeStartObject();
		if (pet.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", pet.getId());
		}
		jgen.writeStringField("name", pet.getName());
		jgen.writeStringField("birthDate", formatter.format(pet.getBirthDate()));

		PetType petType = pet.getType();
		jgen.writeObjectFieldStart("type");
		jgen.writeNumberField("id", petType.getId());
		jgen.writeStringField("name", petType.getName());
		jgen.writeEndObject();

		Owner owner = pet.getOwner();
		jgen.writeObjectFieldStart("owner");
		jgen.writeNumberField("id", owner.getId());
		jgen.writeStringField("firstName", owner.getFirstName());
		jgen.writeStringField("lastName", owner.getLastName());
		jgen.writeStringField("address", owner.getAddress());
		jgen.writeStringField("city", owner.getCity());
		jgen.writeStringField("telephone", owner.getTelephone());
		jgen.writeEndObject();

		jgen.writeArrayFieldStart("visits");
		for (Visit visit : pet.getVisits()) {
			jgen.writeStartObject();
			jgen.writeNumberField("id", visit.getId());
			jgen.writeStringField("date", formatter.format(visit.getDate()));
			jgen.writeStringField("description", visit.getDescription());
			jgen.writeNumberField("pet", visit.getPet().getId());
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeEndObject();
	}

}
