ALTER TABLE "public"."istar_user"
ADD COLUMN "is_verified" bool;

ALTER TABLE "public"."istar_user"
ADD COLUMN "type" varchar(255);

ALTER TABLE "public"."istar_user" RENAME "type" TO "login_type";

CREATE TABLE "public"."user_gamification" (
"id" int4 NOT NULL,
"istar_user" int4,
"skill_objective" int4,
"points" int4,
"coins" int4,
"created_at" timestamp,
"updated_at" timestamp,
PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."user_gamification"
ADD FOREIGN KEY ("istar_user") REFERENCES "public"."istar_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD FOREIGN KEY ("skill_objective") REFERENCES "public"."skill_objective" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "public"."user_gamification"
ADD COLUMN "item_id" int4,
ADD COLUMN "item_type" varchar(255);

ALTER TABLE "public"."skill_objective"
ADD COLUMN "parent_skill" int4,
ADD COLUMN "skill_level_type" varchar(255);
